def call(String imageName, String buildNumber, String projectDir) {
    stage('Build Docker Image') {
        dir(projectDir) {
            sh "docker build -t ${imageName}:${buildNumber} ."
        }
    }
}
