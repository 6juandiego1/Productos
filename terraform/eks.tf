module "eks" {
  source  = "terraform-aws-modules/eks/aws"
  version = "~> 20.0"

  cluster_name    = "eks-products-cluster"
  cluster_version = "1.30"

  # Habilitar acceso público al API de Kubernetes
  cluster_endpoint_public_access = true

  vpc_id     = module.vpc.vpc_id
  subnet_ids = module.vpc.public_subnets

  # Configuración por defecto para los nodos administrados (Managed Node Groups)
  eks_managed_node_groups = {
    default = {
      min_size     = 1
      max_size     = 3
      desired_size = 2

      instance_types = ["t3.medium"]
	  ami_type       = "AL2023_x86_64_STANDARD"
    }
  }

  enable_cluster_creator_admin_permissions = true
}

resource "kubernetes_namespace_v1" "products_app" {
  metadata {
    name = "products-app-prod"
    
    labels = {
      environment = "production"
    }
  }
}
