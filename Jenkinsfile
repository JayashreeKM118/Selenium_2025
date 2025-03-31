pipeline
{
    agent any

    tools{
        maven 'maven'
        }

    stages
    {
        stage('Build')
        {
            steps
            {
                 git 'https://github.com/jglick/simple-maven-project-with-tests.git'
                 bat "mvn -Dmaven.test.failure.ignore=true clean package"
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



        stage("Deploy to QA"){
            steps{
                echo("deploy to qa")
            }
        }



        stage('Regression Automation Test') {
            steps {
                catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE') {
                    git 'https://github.com/JayashreeKM118/Selenium_2025.git'
                    bat "mvn clean test"

                }
            }
        }





        stage('Publish Extent Report'){
            steps{
                     publishHTML([allowMissing: false,
                                  alwaysLinkToLastBuild: false,
                                  keepAll: true,
                                  reportDir: 'reports',
                                  reportFiles: 'TestExecutionReport.html',
                                  reportName: 'HTML Regression Extent Report',
                                  reportTitles: ''])
            }
        }

        stage("Deploy to Stage"){
            steps{
                echo("deploy to Stage")
            }
        }




    }
}