FROM openjdk:11.0-jdk-buster
WORKDIR /opt
COPY build/libs/meli-mutants-infra-0.0.1-SNAPSHOT.jar /opt/app.jar

RUN sh -c 'touch /opt/app.jar'

ENTRYPOINT ["java", "-Xmx4g",  "-Xms512m", "-Dloader.path=/opt", "-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]

