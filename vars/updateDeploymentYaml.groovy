def call(String projectDir, String fileName, String imageName, String buildNumber) {
    stage('Update deployment.yaml') {
        dir(projectDir) {
            sh """
                sed -i 's|image: .*|image: ${imageName}:${buildNumber}|g' ${fileName}
                echo "Updated deployment file:"
                cat ${fileName}
            """
        }
    }
}
