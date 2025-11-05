def call(String projectDir) {
    stage('Build Application') {
        dir(projectDir) {
            sh 'mvn package'
        }
    }
}
