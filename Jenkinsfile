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
    stage("deploy"){
        println "deploy stage"
    }
}
