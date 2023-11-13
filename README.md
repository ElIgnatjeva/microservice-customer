#### Using buildpacks to containerise an app without Dockerfile 
* ./gradlew bootBuildImage
* docker run -d -p 8080:8080 {image generated in the prevoius step}
* docker logs {container ID}
