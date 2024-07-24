pipeline {
    agent any

    environment {
        AWS_ACCESS_KEY_ID = ''
        AWS_SECRET_ACCESS_KEY = ''
        TERRAFORM_BIN = '/home/jenkins/bin'
    }

    stages {
        stage('Install Git') {
            steps {
                // Install Git if not already installed
                sh '''
                if ! command -v git &> /dev/null; then
                  echo "Git is not installed. Installing Git..."
                  sudo yum update -y
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

        stage('Setup AWS Credentials') {
            steps {
                withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', credentialsId: 'aws-credentials']]) {
                    script {
                        env.AWS_ACCESS_KEY_ID = "${AWS_ACCESS_KEY_ID}"
                        env.AWS_SECRET_ACCESS_KEY = "${AWS_SECRET_ACCESS_KEY}"
                    }
                }
            }
        }

        stage('Install Terraform') {
            steps {
                // Install Terraform if not already installed
                sh '''
                if ! command -v terraform &> /dev/null; then
                  echo "Terraform is not installed. Installing Terraform..."
                  mkdir -p $TERRAFORM_BIN
                  wget https://releases.hashicorp.com/terraform/1.0.0/terraform_1.0.0_linux_amd64.zip -O /tmp/terraform.zip
                  unzip -o /tmp/terraform.zip -d $TERRAFORM_BIN
                  if [ $? -ne 0 ]; then
                    echo "Failed to unzip Terraform."
                    exit 1
                  fi
                  echo "export PATH=$TERRAFORM_BIN:$PATH" >> ~/.bashrc
                  source ~/.bashrc
                else
                  echo "Terraform is already installed."
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

    post {
        always {
            echo 'Pipeline has completed.'
        }
        success {
            echo 'Pipeline succeeded.'
        }
        failure {
            echo 'Pipeline failed.'
        }
    }
}


