node{
    stage("checkout"){
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/mahdchek/prep-backend']]])
    }

    stage("unit tests"){
        sh "chmod 777 ./mvnw"
        sh "./mvnw test"
    }

    stage("build"){
        sh "./mvnw clean package -DskipTests"
    }

    stage("quality analyses"){
        sh "./mvn sonar:sonar \\\n" +
                "  -Dsonar.projectKey=backend \\\n" +
                "  -Dsonar.host.url=http://13.58.119.126:11001 \\\n" +
                "  -Dsonar.login=2fb63af68c4fe6720a99d47f9f536241562a17e9"
    }

    stage("deploy"){
        println "deploy stage"
    }
}
