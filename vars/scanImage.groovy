def call(String imageName, String buildNumber) {
    stage('Scan Image') {
        echo "🔍 Scanning Docker image for vulnerabilities..."

        sh """
        trivy image \
          --format table \
          --exit-code 1 \
          --vuln-type os,library \
          --severity CRITICAL,HIGH \
          ${imageName}:${buildNumber}
        """
    }
}
