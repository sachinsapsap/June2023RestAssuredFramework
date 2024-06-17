pipeline 
{
    agent any
    tools {
        maven 'maven'
    }

    stages 
    {
        
        stage('Deploy to QA') {
            steps {
                echo("deploy to qa")
                }
            }
        
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/sachinsapsap/June2023RestAssuredFramework.git'
                    bat "mvn clean install"
                }
            }
        }
                
     
        stage('Publish Allure Reports') {
           steps {
                script {
                    allure([
                        includeProperties: false,
                        jdk: '',
                        properties: [],
                        reportBuildPolicy: 'ALWAYS',
                        results: [[path: '/allure-results']]
                    ])
                }
            }
        }
        
        stage('Deploy to Stage') {
            steps {
                echo("deploy to stage")
                }
            }
            
            stage('Deploy to Prod') {
            steps {
                echo("deploy to Prod")
                }
            }
        
    }
}