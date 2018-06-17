# statement-backend

This is the back end project for the 'Statement Records'. It provides a REST service where RABO bank can request the failed statement records of Customer Statement Processor.
The application is used by the [statement-front application](http://localhost:4200)

The REST service will provide the failed records information of the Processor.
There are two validations:

* The transaction reference should be unique
* the end balance needs to be validated: the value could be displayed in euro Dutch format. For example: € 8,12

The application is written in java 8 and uses spring boot 2.0.2

## Installing / Getting started
The project uses Maven to build all sources and run all tests. It is configured as a [Spring Boot](http://projects.spring.io/spring-boot/) application and ultimately delivers an [executable WAR file](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#build-tool-plugins-maven-packaging) that can be run from the command line or deployed into a servlet container (e.g. Tomcat) as usual.
The most important reason of choosing Spring boot is because of the embedded Tomcat. Application can start up standalone.  It serve not only command line command's but also web pages from the browsers.
So an external Tomcat server is not necessary.

### Requirements
Building and running the project requires:

* [JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 8 (Java) or higher
* [Maven](http://maven.apache.org/download.cgi) 3.3 or higher


### Build it and run
You can build the sources and run with the command:
mvn package && java -jar target/statement-1.0.jar

After a successful build the executable WAR file is available in the **target** directory

This will :
* start an embedded tomcat and publish the REST service at: (http://localhost:8080)


### Test it
To verify that the application runs correctly USE the curl command:
curl localhost:8080

You can also open a  browser with URL: http://localhost:8080

You should see:
[{"reference":112806,"description":"Clothes for Willem Dekker","accountNumber":"NL27SNSB0917829871","startBalance":"91.23","mutation":"+15.57","endBalance":"106.8"},{"reference":112806,"description":"Clothes for Richard de Vries","accountNumber":"NL69ABNA0433647324","startBalance":"90.83","mutation":"-10.91","endBalance":"79.92"},{"reference":112806,"description":"Tickets from Richard Bakker","accountNumber":"NL93ABNA0585619023","startBalance":"102.12","mutation":"+45.87","endBalance":"147.99"}]
