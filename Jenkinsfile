pipeline {
    agent any

    tools {
        maven '3.8.6'    // Jenkins Maven tool name (set in "Global Tool Configuration")
        jdk 'JAVA_HOME'      // Jenkins JDK name (set in "Global Tool Configuration")
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    credentialsId: 'jenkinsadmin',
                    url: 'https://github.com/praveenap13/AllureTestNgCucumber'
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
              allure([includeProperties: false, reportBuildPolicy: 'ALWAYS',
              results: [[path: '/Users/praveena/IdeaProjects/AllureTestNgCucumber/allure-results']]
            ])
        }
    }
}