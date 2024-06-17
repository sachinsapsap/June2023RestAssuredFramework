pipeline 
{
    agent any
    tools {
        maven 'maven'
    }

    stages 
    {
        stage('Build') 
        {
            steps 
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 sh "mvn -Dmaven.test.failure.ignore=true clean package"
            }
            post 
            {
                success 
                {
                    junit '**/target/surefire-reports/TEST-*.xml'
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
        
        stage('Deploy to QA') {
            steps {
                echo("deploy to qa")
                }
            }
        
        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/sachinsapsap/June2023RestAssuredFramework.git'
                    sh "mvn clean install"
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