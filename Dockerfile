FROM openjdk:11

RUN mkdir -p /opt/questionpro/

ADD questionpro-0.0.1-SNAPSHOT.jar /opt/questionpro/

WORKDIR /opt/questionpro/

EXPOSE 8080

CMD java -jar questionpro-0.0.1-SNAPSHOT.jar