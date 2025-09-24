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

//         stage('Generate Allure Report') {
//             steps {
//               allure([includeProperties: false, reportBuildPolicy: 'ALWAYS',
//               results: [[path: '/Users/praveena/IdeaProjects/AllureTestNgCucumber/allure-results']]
//                     ])
//             }
//          }

     }

     post {
             always {
                 allure([
                     results: [[path: '/Users/praveena/IdeaProjects/AllureTestNgCucumber/allure-results']], // Path to your Allure results directory
                     reportBuildPolicy: 'ALWAYS' // Or other policies like 'UNSTABLE', 'FAILURE'
                 ])
             }
         }
}