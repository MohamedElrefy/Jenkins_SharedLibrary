def call(String imageName, String buildNumber) {
    stage('Scan Image') {
        echo "🔍 Scanning Docker image for vulnerabilities..."

        // Run Trivy inside Docker with higher timeout
        sh """
            docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            aquasec/trivy:latest image \
            --timeout 5m \
            --severity HIGH,CRITICAL \
            --exit-code 1 \
            ${imageName}:${buildNumber} \
            || echo '⚠️ Vulnerabilities found — review scan results above.'
        """
    }
}
