terraform {
  required_version = ">= 1.10.0"
  
  backend "s3" {
    bucket       = "product-tfstate-528555979923"
    key          = "product/dev/terraform.tfstate"
    region       = "us-east-1"
    use_lockfile = true
    encrypt      = true
  }

  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.0"
    }
  }
}

provider "aws" {
  region = "us-east-1"
}

# Obtener las zonas de disponibilidad disponibles en la región
data "aws_availability_zones" "available" {
  state = "available"
}
