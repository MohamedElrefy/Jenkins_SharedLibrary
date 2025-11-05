def call(String imageName, String buildNumber, String credId) {
    stage('Push Image to Docker Hub') {
        withCredentials([usernamePassword(credentialsId: credId, usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
            sh """
                docker login -u "$DOCKER_USER" -p "$DOCKER_PASS"
                docker push ${imageName}:${buildNumber}
            """
        }
    }
}
