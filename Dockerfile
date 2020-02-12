FROM java:8-jdk-alpine

COPY ./target/TipoDeCambio-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch TipoDeCambio-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java","-jar","TipoDeCambio-0.0.1-SNAPSHOT.jar"] 
