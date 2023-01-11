pipeline {
    agent any
    tools{
        gradle "gradle742"
    }
    stages {
        stage('git-checkout') {
            steps {
                git 'https://github.com/chilikinow/main_autotests_project.git'
            }
        }
        stage('run-tests') {
            steps {
                sh 'gradle clean test'
            }
        }
        stage('reporting') {
            steps {
                allure includeProperties: false, jdk: 'jdk11', results: [[path: 'build/allure-results']]
            }
        }
    }
}
