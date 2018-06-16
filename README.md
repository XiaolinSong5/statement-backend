# decapitalize-service

This service decapitalizes the first character of the onlineinzage repository, collection 'cases'.

To run this service, do the following:
- run a build: `mvn clean install`
- run the executable jar: `java -jar -Dspring.profiles.active=<env> target/decapitalize-service-0-SNAPSHOT.jar`
where <env> could be either ontw, test, acc, or prod.

mvn package && java -jar target/statement-0.1.0.jar
curl localhost:8080