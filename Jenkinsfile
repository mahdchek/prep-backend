node {
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
            sh "sudo su"
            sh "docker login --username AWS --password eyJwYXlsb2FkIjoib0VrME1KSDRLRWtxT25mNEJiM2U3S29jbDl1ODFKV2dMUmd5V0pndjBZNTd4WDJpYk0xTGc1VVZ5cXF4bk9HM0xpeDFZMVBaM0RpUUhPaWRSTGYveksvZFJESnU2WWlwaStqLzVZVjdpZ3l3b0IreGd4VjNuYy84N3cyMVM1NjZQeldOaFhlVm1IcVhBY1ZpY0IrVFpvYzhQTW1YSWNPeHMrUFhuVjFocnJadUJxb2s1aFZiTjRPWDRXTm5qOFJHNmdBYThDanB0TzRXNU9qL1FZSWMzVndTZDdUWFpRbENlNlFjaXpoSzFkanJ1Z1dHZTI4MjdLZllJNFRaWW9xQnIyZGNJdVhNYzRUQmkzQm15eldPMWk0QytuZVZ2K2xmOG5sV2l0cEVPUWlQTy9KWmVETmJ0V25NcGJGTUFuNjRPb2dKQkhadElvZDUyeWhzeFZsZnd4bndqRFc2NW1zY0R1aDBqVFREN3pWZXVMNkdwVlZ0eXJQZE9hOXF4cEcxdEs4V0RYbHFXQXJYbWw0SS9sa3luUUdXRVlNM0ducFVwdEdaZit3NlE3aEsxRGJ2dytHTkhlRnNjM1pBV2FXUEp6NTFuWk11REVRdDg4bHhFVE51ZXlBZE1Na1QrNFVSV0lvcFBmdVIySmZJTE05S0IzVkg4bExVQkVsV1U1eGQ5bGhwdzVQbmordkRPQTJVNTdwZGt0aXNWMjEyb2RjMjNBVVZKKzFKMVNrNEtUV3BXbmdra3hrZHNydGd2SHpaVmIxeG5OUy9GZTBPazBKRUZvL1BhT3hCdXF6TnV0SnVMM0Y4RmUrYzRRaTBHUjF1K0d5S3pNZDhJVTlONDhQdnNqSUx3RDVXRER0Y0huZkVmQ0VhRjM1MmxUczFUWnFZMEQ4RUhva3ZTVE5XMjJHRlBZRVJmOVVvbnMrQlhxc2FxNFJ4T1pnY2hIazAzQTZmMk83T25ndVcyOWRjY3NDcVQ3QUNoQlJaNVl1Mm9YMW1udmQ0Zm9pYmZ1S2JkQ09xR1E1aFU5N1Rwdm9ad1RLcFI4VTVtR2ZJQXlPOHdKVyt4Mk5lV1JFbUo5NGxkTFNhNlAxSU9YQm0wc3JPSk1mUndXVjJkb3RVa2p2amhpNUV1UTRiemZtZFRxb2N0SkZRU1MraWlNT1kvOUlKNVlQNGdWWThVTWtPdmJRWGpkUjdSVnVHZzVEWGp4a2FVZm1ldkVneDM0T21TeTBoWGUveG5VL0lSdFA0eFg2WGNsOEJZZklodjhpa1BmYTdmcm0zQUJXQkV5aUxUYTI5TVNVamtPMGFVbndWVElxdVNtV0VjemhmdHFYSTVtdlIvci9UbFZVMWNGNXpiUEpQTDNzbnpoa3o3OXBSTVloS0JRSjRQMjJ5NDUyWDR2cDBGbzl3TGVYaDhGcldMZ1c0K3lVaHdlYTZTRmkwZGJ6VzhOd3NYOFZSZlVua3E2eFRQU0xzVDBYZDZHSEFDVDdyZTlnanpORU1tNlU9IiwiZGF0YWtleSI6IkFRSUJBSGhYbUlKOVhINUNFRGRNWS90NnpLbW8vWnFZbDJ4S3NLR0txU05xUFpvTytnRklxUUxGN09qckdqQU0wTnZwSC9HdEFBQUFmakI4QmdrcWhraUc5dzBCQndhZ2J6QnRBZ0VBTUdnR0NTcUdTSWIzRFFFSEFUQWVCZ2xnaGtnQlpRTUVBUzR3RVFRTXRKZVh5VWZHamMxRFZrQXZBZ0VRZ0R1R0x4dTFaSGYya1JyaFVtUElLVzZFaTBSTWhsM2thWFFrcmJ1akZlOWUxbWNaY2d5Q3J4QU5kQUcxclhrdTZ0Z2ZtclJ5R0ptWSt4NWkzQT09IiwidmVyc2lvbiI6IjIiLCJ0eXBlIjoiREFUQV9LRVkiLCJleHBpcmF0aW9uIjoxNjUzNzExNTE1fQ== public.ecr.aws/v0r2h1q6"
            sh "docker tag backend:latest public.ecr.aws/v0r2h1q6/backend:latest"
            sh "docker push public.ecr.aws/v0r2h1q6/backend:latest"
        }
    }


    stage("deploy") {
        println "deploy stage"
    }
}
