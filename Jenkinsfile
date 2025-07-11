pipeline {
	//Select agent:agent
    agent any

	//Select maven:maven
    tools {
        maven 'Maven'
    }

	//trigers comming from the github webhook
    triggers {
        // Poll SCM every 10 minutes
        pollSCM('H/15 * * * *')

        // Scheduled trigger at 09:30 AM South Africa time (07:30 UTC) Monday to Friday
        cron('30 7 * * 1-5')
    }

	//Select Stage:stages
    stages {
        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Test') {
           steps {
               script{
                     //Enabling the results to execute in case there's a failure
                     catchError(buildResult:'UNSTABLE', stageResult:'FAILURE'){
                     //For credentials, we use credentials plugin
                     //like this withCredentials([usernamePassword(
                     //  credentialsId: 'BookerId', //declared in your jenkins/like github & has username and password
                     //  usernameVariable: "BOOKER_USERNAME",  //name matches exactly in the code
                     //  passwordVariable: 'BOOKER_PASSWORD')]) //password matches exactly in the code
                     //{
                       sh "mvn test -P${profile}"
                    // } Tests run inside the credentials to bind the environmental variables with it.

                   }
               }
           }
      }

        stage('Reports') {
            steps {
                script {
                    allure includeProperties: false, jdk: '', results: [[path: 'allure-results']]
                }
            }
        }
    }
}
