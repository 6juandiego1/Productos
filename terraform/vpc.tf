module "vpc" {
  source  = "terraform-aws-modules/vpc/aws"
  version = "~> 5.0"

  name = "eks-product-vpc"
  cidr = "10.0.0.0/16"

  azs            = slice(data.aws_availability_zones.available.names, 0, 2)
  public_subnets = ["10.0.1.0/24", "10.0.2.0/24"]

  # Desactivamos NAT Gateways para no generar costos en la práctica
  enable_nat_gateway   = false
  single_nat_gateway   = false
  enable_dns_hostnames = true

  # Importante: Permitir que las instancias en subredes públicas reciban IP pública automáticamente
  map_public_ip_on_launch = true

  public_subnet_tags = {
    "kubernetes.io/role/elb" = "1"
  }
}

resource "aws_security_group" "vpc_link_sg" {
  name        = "eks-vpc-link-sg"
  description = "Security group for API Gateway VPC Link"
  vpc_id      = module.vpc.vpc_id

  # Permitir salida hacia el ALB
  egress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["10.0.1.0/24", "10.0.2.0/24"]
  }
}
