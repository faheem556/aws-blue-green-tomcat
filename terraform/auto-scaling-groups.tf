resource "aws_autoscaling_group" "blue" {
  name             = "${var.app_name}-blue"
  desired_capacity = local.blue_instances
  min_size         = 0
  max_size         = var.instance_count
  vpc_zone_identifier = [
    aws_default_subnet.default_az1.id,
    aws_default_subnet.default_az2.id
  ]

  launch_configuration = aws_launch_configuration.blue.name
  target_group_arns    = [aws_lb_target_group.blue.arn]
  health_check_type    = "ELB"

  depends_on = [aws_lb.app_lb]

  lifecycle {
    create_before_destroy = true
  }
}

resource "aws_autoscaling_group" "green" {
  name             = "${var.app_name}-green"
  desired_capacity = local.green_instances
  min_size         = 0
  max_size         = var.instance_count
  vpc_zone_identifier = [
    aws_default_subnet.default_az1.id,
    aws_default_subnet.default_az2.id
  ]

  launch_configuration = aws_launch_configuration.green.name
  health_check_type    = "ELB"

  depends_on = [aws_lb.app_lb]

  target_group_arns = [aws_lb_target_group.green.arn]

  lifecycle {
    create_before_destroy = true
  }
}