pipeline {
    agent any

    environment {
        // Use Jenkins credentials to inject AWS access key ID and secret access key
        AWS_ACCESS_KEY_ID = credentials('aws-access-key-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-secret-access-key')
    }

    stages {
        stage('Install Git') {
            steps {
                // Install Git if not already installed
                sh '''
                if ! command -v git &> /dev/null; then
                  echo "Git is not installed. Installing Git..."
                  sudo yum update
                  sudo yum install git -y
                else
                  echo "Git is already installed."
                fi
                '''
            }
        }

        stage('Checkout') {
            steps {
                // Checkout the source code from the private GitHub repository
                git branch: 'main', credentialsId: 'jenkins-private-repo', url: 'https://github.com/bermetakub/CI-CD.git'
            }
        }

        stage('Install Terraform') {
            steps {
                // Install Terraform if not already installed
                sh '''
                if ! command -v terraform &> /dev/null; then
                  wget https://releases.hashicorp.com/terraform/1.0.0/terraform_1.0.0_linux_amd64.zip
                  unzip terraform_1.0.0_linux_amd64.zip
                  sudo mv terraform /usr/local/bin/
                fi
                '''
            }
        }

        stage('Terraform Init') {
            steps {
                // Initialize Terraform
                withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    credentialsId: 'aws-credentials',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                ]]) {
                    sh 'terraform init'
                }
            }
        }

        stage('Terraform Plan') {
            steps {
                // Run Terraform plan
                withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    credentialsId: 'aws-credentials',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                ]]) {
                    sh 'terraform plan'
                }
            }
        }

        stage('Terraform Apply') {
            steps {
                // Apply the Terraform plan
                withCredentials([[
                    $class: 'AmazonWebServicesCredentialsBinding',
                    credentialsId: 'aws-credentials',
                    accessKeyVariable: 'AWS_ACCESS_KEY_ID',
                    secretKeyVariable: 'AWS_SECRET_ACCESS_KEY'
                ]]) {
                    sh 'terraform apply -auto-approve'
                }
            }
        }
    }
}



