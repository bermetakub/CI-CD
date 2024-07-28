def call(String deployTarget, String image) {
    echo "Deploying to target: ${deployTarget} with image: ${image}"
    sh "ssh bermet@${deployTarget} 'docker pull ${image} && docker run -d -p 8083:80 ${image}'"
}
