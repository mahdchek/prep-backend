node{
    stage("checkout"){
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/mahdchek/prep-backend']]])
    }
    stage("build"){
        sh "chmod 777 ./mvnw && ./mvnw clean build -DskipTests"
    }
    stage("deploy"){
        println "deploy stage"
    }
}
