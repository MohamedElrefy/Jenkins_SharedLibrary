def call(String imageName, String buildNumber) {
    stage('Scan Image') {
        script {
            echo "🔍 Scanning Docker image for vulnerabilities..."
            sh '''
                if ! command -v trivy &> /dev/null; then
                    echo "Installing Trivy..."
                    sudo apt-get update -y && apt-get install -y wget
                    sudo wget -qO - https://aquasecurity.github.io/trivy-repo/deb/public.key | gpg --dearmor > /usr/share/keyrings/trivy.gpg
                    echo "deb [signed-by=/usr/share/keyrings/trivy.gpg] https://aquasecurity.github.io/trivy-repo/deb stable main" | tee /etc/apt/sources.list.d/trivy.list
                    sudo apt-get update -y && apt-get install -y trivy
                fi
            '''
            sh "trivy image --severity HIGH,CRITICAL --exit-code 1 ${imageName}:${buildNumber} || echo '⚠️ Vulnerabilities found, review scan results above.'"
        }
    }
}
