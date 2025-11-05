def call(String projectDir) {
    stage('Run Unit Tests') {
        dir(projectDir) {
            sh 'mvn test'
        }
    }
}
