provider "aws" {
  region = "us-east-1"
}

#resource "aws_instance" "bermet-example" {
#  ami           = "ami-0427090fd1714168b"
#  instance_type = "t2.micro"
#
#  tags = {
#    Name = "bermeta-example-instance"
#  }
#}
resource "aws_key_pair" "bermet_example" {
  key_name = "bermet-example-key"
}
