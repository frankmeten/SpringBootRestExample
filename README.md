# BirdSighting

Prerequisite

1. Java installed
2. docker installed
3. maven installed

open terminal go to the directory that birdSighting.zip located

unzip birdSighting.zip
cd birdSighting
RUN docker-compose -f src/main/resources/docker-compose.yml up
RUN mvn install -DskipTests=true
RUN java -jar target/
RUN java -jar target/birdsightrestapi-0.0.1-SNAPSHOT.jar
 
The app will start running at http://localhost:8080 
  
Explore Rest APIs:  reference API_documentation.txt