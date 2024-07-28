def call(String server, String image) {
    sshagent(['deploy-credentials']) {
        sh "ssh bermet@${172.20.10.10} 'docker pull ${image} && docker run -d -p 8083:80 ${image}'"
    }
}

