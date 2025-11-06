def call(String imageName, String buildNumber) {
    stage('Scan Image') {
        echo "🔍 Scanning Docker image for vulnerabilities..."

        // Run Trivy inside Docker with higher timeout
        sh """
            docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            -v /tmp/trivy-cache:/root/.cache/ \
            aquasec/trivy:latest image \
            --timeout 20m \
            --cache-dir /root/.cache/ \
            --severity HIGH,CRITICAL \
            --exit-code 1 \
            ${imageName}:${buildNumber}
        """
    }
}
