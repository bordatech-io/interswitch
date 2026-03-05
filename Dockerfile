FROM eclipse-temurin:17-jre

WORKDIR /app
RUN mkdir -p /app/logs

COPY bridge-service/target/bridge-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 9093
EXPOSE 9094
EXPOSE 9095
EXPOSE 9096 

ENTRYPOINT ["java","-jar","/app/app.jar"]

