pipeline{
    //Select agent:agent
    agent any

    //Select maven:maven
    tools {
      maven 'Maven'
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
                       sh "mvn test -Pregression"
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