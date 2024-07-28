def call(String imageName) {
    sh """
        docker build -t ${imageName} .
        docker push ${imageName}
    """
}
