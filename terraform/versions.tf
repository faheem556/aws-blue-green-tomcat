
terraform {
  required_version = "~>0.13.5"
  backend "s3" {}
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.0"
    }
    random = {
      source  = "hashicorp/random"
      version = "~> 3.0.1"
    }
    local = {
      source  = "hashicorp/local"
      version = "~> 2.0.0"
    }
    tls = {
      source  = "hashicorp/tls"
      version = "~> 3.0.0"
    }
  }
}

provider "aws" {
  region = var.infra_region

  assume_role {
    role_arn     = "arn:aws:iam::role_id:role/terraform"
    session_name = "faheem.memon"
  }
}
