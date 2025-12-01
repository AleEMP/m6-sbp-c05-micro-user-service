pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'aleemp/m6-sbp-c05-micro-user-service'
        DOCKER_TAG = 'latest'
        DOCKER_CREDS_ID = 'docker-hub-credentials'
    }

    stages {
        stage('Preparation') {
            steps {
                sh 'chmod +x mvnw'
            }
        }

        stage('Build & Test') {
            steps {
                echo 'Compilando y Ejecutando Pruebas Unitarias/Integración...'
                sh './mvnw clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Construyendo imagen Docker...'
                script {
                    // Construye la imagen usando el Dockerfile del proyecto
                    dockerImage = docker.build("${DOCKER_IMAGE}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                echo 'Subiendo imagen a Docker Hub...'
                script {
                    docker.withRegistry('', DOCKER_CREDS_ID) {
                        dockerImage.push()
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Limpiando imágenes locales para ahorrar espacio...'
            sh "docker rmi ${DOCKER_IMAGE}:${DOCKER_TAG} || true"
        }
    }
}