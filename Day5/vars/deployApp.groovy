def call(String server, String imageName) {
    sshagent(['dockerhub-creds']) {
        sh """
            ssh -o StrictHostKeyChecking=no ${server} 'docker pull ${imageName} && docker run -d -p 80:8083 ${imageName}'
        """
    }
}
