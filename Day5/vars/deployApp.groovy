def call(String server, String imageName) {
    sshagent(['dockerhub-creds']) {
        sh """
            ssh -o StrictHostKeyChecking=no ${server} 'docker pull ${imageName} && docker run -d -p 8083:80 ${imageName}'
        """
    }
}
