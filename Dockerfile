FROM openjdk:8-jdk-alpine
MAINTAINER amassillo@gmail.com
EXPOSE 8080
ENV WORKING_DIR /dgchallenge
RUN mkdir -p $WORKING_DIR
WORKDIR $WORKING_DIR
ADD target/minesweeper-api-0.0.1-SNAPSHOT.jar $WORKING_DIR
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar minesweeper-api-0.0.1-SNAPSHOT.jar" ]