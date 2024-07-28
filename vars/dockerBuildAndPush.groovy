def call(String branch, String env) {
    def imageName = "alymkulovabk/simpleapp:${branch}-${env}"
    sh "docker build -t ${imageName} ."
    sh "docker push ${imageName}"
}

