# BirdSighting

Base on https://github.com/chalamalasettichamundeswari/SpringBootRestExample
https://pavankjadda.medium.com/search-data-across-multiple-columns-using-spring-data-jpa-fc0a24799991

Prerequisite

1. Java installed
2. docker installed
3. maven installed

open terminal go to the directory that birdSighting.zip located

unzip -d birdSighting birdSighting.zip
cd birdSighting

-- start mysql
RUN docker-compose -f src/main/resources/docker-compose.yml up

RUN mvn install

RUN java -jar target/birdsightrestapi-0.0.1-SNAPSHOT.jar

 -- maybe you should replace localhost with your server ip if you visit from another host

The app will start running at http://localhost:8080 
  
Explore Rest APIs:  reference http://localhost:8080/birdsightapi/swagger-ui/index.html


