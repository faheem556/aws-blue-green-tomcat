resource "tls_private_key" "global_key" {
  algorithm = "RSA"
  rsa_bits  = 2048
}

resource "aws_key_pair" "app_key_pair" {
  key_name_prefix = var.app_name
  public_key      = tls_private_key.global_key.public_key_openssh
}

resource "aws_iam_role" "ec2_s3_access_role" {
  name = "ec2_s3_access_role"
  path = "/"

  assume_role_policy = <<-EOF
  {
    "Version": "2012-10-17",
    "Statement": [
        {
            "Action": "sts:AssumeRole",
            "Principal": {
               "Service": "ec2.amazonaws.com"
            },
            "Effect": "Allow",
            "Sid": ""
        }
    ]
  }
  EOF
}

resource "aws_iam_policy_attachment" "s3_readall_attach" {
  name       = "s3-readall-attach"
  roles      = [aws_iam_role.ec2_s3_access_role.name]
  policy_arn = data.aws_iam_policy.s3_readonly_access.arn
}

resource "aws_iam_instance_profile" "app_profile" {
  name = "${var.app_name}-profile"
  role = aws_iam_role.ec2_s3_access_role.name
}

resource "aws_launch_configuration" "blue" {
  name_prefix   = "${substr(var.app_name, 0, 6)}-blue"
  image_id      = data.aws_ami.ubuntu.id
  instance_type = "t2.micro"

  security_groups             = [aws_security_group.app_sg_allowall.id]
  associate_public_ip_address = false
  key_name                    = aws_key_pair.app_key_pair.key_name
  iam_instance_profile        = aws_iam_instance_profile.app_profile.name

  user_data = base64encode(templatefile(join("/", [path.module, "user_data/config"]), {
    tomcat_version = var.tomcat_version
    app_s3_archive = var.app_blue_s3_archive
  }))

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_launch_configuration" "green" {
  name_prefix   = "${substr(var.app_name, 0, 6)}-green"
  image_id      = data.aws_ami.ubuntu.id
  instance_type = "t2.micro"

  security_groups             = [aws_security_group.app_sg_allowall.id]
  associate_public_ip_address = false
  key_name                    = aws_key_pair.app_key_pair.key_name
  iam_instance_profile        = aws_iam_instance_profile.app_profile.name

  user_data = base64encode(templatefile(join("/", [path.module, "user_data/config"]), {
    tomcat_version = var.tomcat_version
    app_s3_archive = var.app_green_s3_archive
  }))

  lifecycle {
    create_before_destroy = true
  }
}