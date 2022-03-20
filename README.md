# BirdSighting

Base on https://github.com/chalamalasettichamundeswari/SpringBootRestExample

Prerequisite

1. Java installed
2. docker installed
3. maven installed

open terminal go to the directory that birdSighting.zip located

unzip -d birdSighting birdSighting.zip
cd birdSighting
RUN docker-compose -f src/main/resources/docker-compose.yml up

option 1: if you do not want sample data
RUN mvn install -DskipTests=true

option 2: if you do want some sample data
RUN mvn install

RUN java -jar target/birdsightrestapi-0.0.1-SNAPSHOT.jar

 -- maybe you should replace localhost with your server ip

The app will start running at http://localhost:8080 
  
Explore Rest APIs:  reference http://localhost:8080/birdsightapi/swagger-ui/index.html


