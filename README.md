# ConnectivityFinder
 
# Spring-boot-Cities-Connection
Given a file with delimited component pairs, this API informs whether two components are connected(directly or transitively). Components can be Cities , States or anything that can be represented as a Graph data structure.

For this use case , File contains a list of city pairs (one pair per line, comma separated), which indicates that there’s a road between those cities.
There could be a direct connection or series of roads connection (intermediate city hops), both qualifies as connected.

### Documentation Contents:
1. [Summary](#summary)
2. [Technologies](#technologies) 
3. [Rest API](#rest-api)
4. [Implementation](#implementation)
5. [Start the application](#start-the-application)
6. [Test](#test)
7. [Swagger](#swagger)
8. [Notes](#notes)


### Summary
Provided Origin and Destination cities, this service identifies whether they are connected. 

### Technologies
Spring Boot, Java 8, Swagger, Spring Boot test, Maven, Junit

### Rest API
GET /connected?origin=<origin-city>&destination=<destination-city>

**Response**<br></br>
yes - If connected<br></br>
no - If not connected<br></br>
no - All other scenarios like invalid data. 

### Implementation
The City Connection is perfect use case for a **Graph data structure**. A Graph structure have been created with the input data provided (city.txt).<br></br> 
**Breadth first traversal** technique is used to traverse the Graph and connection between cities is identified.
 
1. First using the ResourceLoader, input file - city.txt is read from classpath (city.txt is placed inside resources folder). A City Connection Graph have been created 
2. Using File NIO, the input file is read and utilizing Java 8 Streams api, the connections (lines) are read and a **City Graph** is constructed.
3. First two steps happens during application startup.
4. Now, once Spring-Boot application is up and running, the api endpoint _/connected_ is invoked by passing Origin and Destination cities.
5. Using Breadth First Traversal technique, the Graph is traversed and the connection is identified. 

## Start the application
Since Spring-boot app and the package is jar and embedded web container is used. You can start the application as a regular Java application<br></br>
In the terminal Navigate to Main Configuration and start as a java class. If you are using any IDE you may start with the option the IDE provides.<br></br>
At same time Spring-Boot Maven plugin is included in pom.xml, so you can also start using below command.<br></br>

**Main Configuration Class:** com.connectivity.finder.ConnectivityFinderApplication

```
mvn spring-boot:run
```
**Note:** Application runs on port 2799. And, the port to run is already configured in the property file so need not have to specify in the command line. 

## Test
#### Via Browser
```
http://localhost:2799/connected?origin=Boston&destination=Newark
http://localhost:2799/connected?origin=Boston&destination=Philadelphia
http://localhost:2799/connected?origin=Trenton&destination=Albany
http://localhost:2799/connected?origin=Phoenix&destination=Austin
http://localhost:2799/connected?origin=Boston&destination=Albany
http://localhost:2799/connected?origin=Boston&destination=Trenton
http://localhost:2799/connected?origin=Philadelphia&destination=Albany
```
#### Via Terminal 
Do a cURL
```
curl --location --request GET 'http://localhost:8080/connected?origin=Boston&destination=Newark'
```

And, Unit test is written for all the classes. **Spring Boot Test** capability is leveraged to the fullest.

## Swagger
```
http://localhost:2799/v2/api-docs
http://localhost:2799/swagger-ui/
```

## Notes
```
- Commas will not appear within city names in the file. For example, "Washington, D.C." will not appear in the file as a city name
- Caching can be implemented to store the response for a combination of origin and destination to avoid running the same login again
- Filter can be added to intercept the incoming request and clean up any unwanted characters 
- To change properties in the resource file at run time without requiring a deployment , it should be externalized

```
