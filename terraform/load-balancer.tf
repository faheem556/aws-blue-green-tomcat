resource "aws_default_subnet" "default_az1" {
  availability_zone = "us-east-1a"
}

resource "aws_default_subnet" "default_az2" {
  availability_zone = "us-east-1b"
}

resource "aws_default_vpc" "default" {}

resource "aws_security_group" "app_sg_allowall" {
  name        = "${var.app_name}-allow-http"
  description = "allow all traffic"

  ingress {
    from_port   = "8080"
    to_port     = "8080"
    protocol    = "6"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = "443"
    to_port     = "443"
    protocol    = "6"
    cidr_blocks = ["0.0.0.0/0"]
  }

  egress {
    from_port   = "0"
    to_port     = "0"
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }
}

resource "aws_lb" "app_lb" {
  name               = "${var.app_name}-lb"
  internal           = false
  load_balancer_type = "application"
  subnets = [
    aws_default_subnet.default_az1.id,
    aws_default_subnet.default_az2.id
  ]
  security_groups = [aws_security_group.app_sg_allowall.id]
}

resource "aws_lb_target_group" "blue" {
  name     = "blue-tg-${substr(var.app_name, 0, 8)}"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_default_vpc.default.id

  health_check {
    protocol = "HTTP"
    path     = "/health"
    port     = 8080
    matcher  = "200"
    timeout  = 5
    interval = 10
  }
}

resource "aws_lb_target_group" "green" {
  name     = "green-tg-${substr(var.app_name, 0, 8)}"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = aws_default_vpc.default.id

  health_check {
    protocol = "HTTP"
    path     = "/health"
    port     = 8080
    matcher  = "200"
    timeout  = 5
    interval = 10
  }
}

resource "aws_lb_listener" "app_listener" {
  load_balancer_arn = aws_lb.app_lb.arn
  port              = "80"
  protocol          = "HTTP"

  default_action {
    type = "forward"
    forward {
      target_group {
        arn    = aws_lb_target_group.blue.arn
        weight = lookup(local.traffic_dist_map[var.traffic_distribution], "blue", 100)
      }
      target_group {
        arn    = aws_lb_target_group.green.arn
        weight = lookup(local.traffic_dist_map[var.traffic_distribution], "green", 0)
      }
      stickiness {
        enabled  = true
        duration = 20
      }
    }
  }
}