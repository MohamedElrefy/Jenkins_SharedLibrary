def call(String projectDir, String fileName, String tokenId, String apiServerId) {
    stage('Deploy to Kubernetes') {
        dir(projectDir) {
            withCredentials([
                string(credentialsId: tokenId, variable: 'TOKEN'),
                string(credentialsId: apiServerId, variable: 'APISERVER')
            ]) {
                sh """
                    kubectl --token="$TOKEN" \
                            --server="$APISERVER" \
                            --insecure-skip-tls-verify=true \
                            apply -f ${fileName}
                """
            }
        }
    }
}
