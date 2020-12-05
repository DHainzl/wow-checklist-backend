FROM openjdk:14
#VOLUME /tmp
#ARG JAR_FILE
COPY target/wow-checklist-backend-*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
