//TODO Need to troubleshoot added file due future scope

pipeline {
    agent any

    environment {
        DOCKER_IMAGE_NAME = 'your-docker-image-name'
        DOCKER_TAG = 'latest'
        REGISTRY = 'your-docker-registry' // e.g., 'docker.io' or a private registry URL
        REGISTRY_CREDENTIALS_ID = 'docker-registry-credentials' // Jenkins credentials ID for Docker registry
    }

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from the repository
                checkout scm
            }
        }

        stage('Build Application') {
            steps {
                // Build the Spring Boot application using Gradle
                sh './gradlew build'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    // Build the Docker image
                    def dockerImage = docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_TAG}")
                }
            }
        }

        stage('Push Docker Image') {
            when {
                expression { return env.REGISTRY != '' }
            }
            steps {
                script {
                    // Log in to Docker registry
                    docker.withRegistry("https://${REGISTRY}", "${REGISTRY_CREDENTIALS_ID}") {
                        // Push the Docker image to the registry
                        docker.image("${DOCKER_IMAGE_NAME}:${DOCKER_TAG}").push("${DOCKER_TAG}")
                    }
                }
            }
        }
    }

    post {
        always {
            // Clean up Docker images to free space
            sh 'docker system prune -af'
        }

        success {
            echo 'Pipeline succeeded!'
        }

        failure {
            echo 'Pipeline failed!'
        }
    }
}
