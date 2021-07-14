
# Introduction

The application is as a SpringBoot micro-service. After cloning the application it can be started from the command line using the following cmd: **mvn spring-boot:run** from the root of the application. 

## Usage
The REST API of the micro-service can be accessed and explored here:
> http://localhost:8080/swagger-ui.html.

To access the built in H2 database go here:
>http://localhost:8080/h2-console/login.do
>sa/password

## Unit Test Coverage
I only provided samples of what I would normally use more extensively for the different aspects of the application. 
- Controllers covered with SpringCloud Contract
- Repository layer I deemed to simple to warrant tests, normaly would need Component tests
- Service Layer covered with Mockito 

>Lacking entirely are Integration tests.

## Design

I designed the solution to solve the simple problem at hand, that is to say I didn't overthink or over-engineer it.
For brevity, I omitted security and tracing filters that would otherwise be necessary in such a microservice.

There are a number of ways to address the compaction of the years matches to be published as the starting off rankings
for the following year. Using, flyway or code in conjunction with the build process or even database base partitioning
I would be happy to discuss going further. 
  
