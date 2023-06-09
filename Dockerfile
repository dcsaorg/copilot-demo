# containerize the copilot-demo app with a lightweight ubuntu image
# copy copilot-demo jar file to the container
# run the jar file
# expose port 8080
FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
ARG DB_HOST
ENV DB_HOST $DB_HOST
COPY target/copilot-demo-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","/copilot-demo-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080