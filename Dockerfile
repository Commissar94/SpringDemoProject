#FROM adoptopenjdk/openjdk15
#ARG JAR_FILE=target/SpringDemoProject-0.0.1-SNAPSHOT.jar
#WORKDIR /opt/app
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar", "app.jar"]

FROM adoptopenjdk/openjdk11
ADD target/spring-boot-mysql.jar spring-boot-mysql.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "spring-boot-mysql.jar"]