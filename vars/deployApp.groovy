def call(String server, String image) {
    sshagent(['id_rsa']) {
        sh "ssh bermet@${172.20.10.10} 'docker pull ${image} && docker run -d -p 8083:80 ${image}'"
    }
}

