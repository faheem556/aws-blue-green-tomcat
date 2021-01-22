data aws_ami amazon_linux_2 {
  most_recent = true

  filter {
    name   = "name"
    values = ["amzn2-ami-hvm-*-ebs"]
  }

  owners = ["amazon"]
}

data "aws_ami" "ubuntu" {
  most_recent = true
  owners      = ["099720109477"] # Canonical

  filter {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-bionic-18.04-amd64-server-*"]
  }

  filter {
    name   = "virtualization-type"
    values = ["hvm"]
  }
}

data "aws_iam_policy" "s3_readonly_access" {
  arn = "arn:aws:iam::aws:policy/AmazonS3ReadOnlyAccess"
}
