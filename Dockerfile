FROM eclipse-temurin:17-jdk

VOLUME /tmp
#ARG JAR_FILE
RUN mkdir -p /usr/src/app/

WORKDIR /usr/src/app/
COPY [".", "./"]
RUN ls
RUN mkdir -p /usr/src/app/logs


EXPOSE 9093
EXPOSE 9094
EXPOSE 9095
EXPOSE 9096


#ENTRYPOINT ["java","-jar","bridge-service/target/bridge-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-cp","bridge-service/target/bridge-0.0.1-SNAPSHOT.jar:bridge-service/src/main/resources/lib/*:bridge-service/src/main/resources/","org.springframework.boot.loader.JarLauncher"]



