FROM openjdk:18
ADD target/theatre-app.jar theatre-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","theatre-app.jar"]