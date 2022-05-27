node{
    stage("checkout"){
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/mahdchek/prep-backend']]])
    }
    stage("build"){
        println "build stage"
    }
    stage("deploy"){
        println "deploy stage"
    }
}
