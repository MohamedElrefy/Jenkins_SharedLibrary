def call(String imageName, String buildNumber) {
    stage('Delete Local Image') {
        sh "docker rmi -f ${imageName}:${buildNumber}"
    }
}
