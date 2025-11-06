def call(String imageName, String buildNumber) {
    stage('Scan Image') {
        echo "🔍 Scanning Docker image for vulnerabilities..."

        sh """
            docker run --rm \
            -v /var/run/docker.sock:/var/run/docker.sock \
            aquasec/trivy:latest image \
            --timeout 20m \
            --scanners vuln \
            --severity HIGH,CRITICAL \
            --exit-code 1 \
            ${imageName}:${buildNumber} \
            || echo '⚠️ Vulnerabilities found — review scan results above.'
        """
    }
}
