FROM openjdk:8-jdk-alpine
COPY /target/*.jar base-admin-0.0.1.jar
#本地打包路径
EXPOSE 8080
ENTRYPOINT ["java","-jar", "-Duser.timezone=GMT+8","base-admin-0.0.1.jar"]
