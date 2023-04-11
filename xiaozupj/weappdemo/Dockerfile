FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/weappdemo.jar weappdemo.jar
ENTRYPOINT ["java","-jar","/weappdemo.jar", "&"]
