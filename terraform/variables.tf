variable infra_region {
  description = "Region where infrastructure will be created"
  default     = "us-east-1"
}

variable "tomcat_version" {
  description = "Tomcat version to install on the machine"
  default     = "9.0.41"
}

variable "app_name" {
  default     = "funfact"
  description = "Application name"
}

variable "app_blue_s3_archive" {
  description = "Blue WAR file s3 URL"
}

variable "app_green_s3_archive" {
  description = "Green WAR file s3 address"
}

variable "traffic_distribution" {
  description = "Levels of traffic distribution"
  type        = string
}

variable "instance_count" {
  default     = 4
  type        = number
  description = "Number of instances serving the traffic"
}