def call(String imageName, String buildNumber) {
    stage('Scan Image') {
        agent {
            docker {
                image 'aquasec/trivy:latest'
                args '-v /var/run/docker.sock:/var/run/docker.sock'
            }
        }
        steps {
            script {
                echo "🔍 Scanning Docker image for vulnerabilities..."
                sh """
                    trivy image --severity HIGH,CRITICAL --exit-code 1 ${imageName}:${buildNumber} \
                    || echo '⚠️ Vulnerabilities found — review scan results above.'
                """
            }
        }
    }
}
