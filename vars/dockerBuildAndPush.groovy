def call(String imageName) {
    withCredentials([usernamePassword(credentialsId: 'dockerhub-creds', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
        sh """
            docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
            docker build -t ${imageName} .
            docker push ${imageName}
        """
    }
}

// def call(String branch, String env) {
//     def imageName = "alymkulovabk/simpleapp:${branch}-${env}"
//     sh "docker build -t ${imageName} ."
//     sh "docker push ${imageName}"
// }

