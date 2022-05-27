node("master") {
    stage("checkout") {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/mahdchek/prep-backend']]])
    }
    stage("unit tests") {
        sh "chmod 777 ./mvnw"
        sh "./mvnw test"
    }

    stage("build") {
        sh "./mvnw clean package -DskipTests"
        stash includes: 'target/backend*.jar', name: 'livrable-backend'
        stash includes: 'Dockerfile', name: 'Dockerfile'
    }

    stage("quality analyses") {
        sh "./mvnw sonar:sonar \\\n" +
                "  -Dsonar.projectKey=backend \\\n" +
                "  -Dsonar.host.url=http://3.145.73.31:11001 \\\n" +
                "  -Dsonar.login=63cce293b7e1a6093356f147f8a57aa9b44e63f4"
    }

    node("vm-ic") {
        stage("build image") {
            unstash 'livrable-backend'
            unstash 'Dockerfile'
            sh "sudo docker build -t backend ."
        }
        stage("push image"){
            sh "sudo aws ecr-public get-login-password --region us-east-1 | sudo docker login --username AWS --password-stdin public.ecr.aws/v0r2h1q6"
            sh "sudo docker tag backend:latest public.ecr.aws/v0r2h1q6/backend:latest"
            sh "sudo docker push public.ecr.aws/v0r2h1q6/backend:latest"
        }
    }

    node("vm-deploy"){
        stage("deploy") {
            try{
               sh "sudo docker stop backend"
               sh "sudo docker rm backend"
            }catch(Exception e){
                println "aucun cntenur n'est lancé"
            }
           sh "sudo docker run --name backend -d -p 8080:8080 public.ecr.aws/v0r2h1q6/backend"
        }
    }
}
