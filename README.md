<h2>Ecommerce REST API to deep dive into Java and Spring Boot</h2>

<h3>Overview</h3>

The purpose of this application is to practice some technologies of a Rest API backend. So, this application has just a few features of what is expected of an e-commerce server, such as create and get orders.

I used the following technologies: 
- Spring Data JPA
- Hibernate
- Postgres database
- Lombok
- Maven
- Spring Actuator
- Spring RestClient
- 3rd party API integration
- External configuration with application.yaml
- Initial load from JSON file

Postgres database I run locally in a Docker container with the following command:
`docker run --name rezendeDb -p 5455:5432 -e POSTGRES_USER=rezende -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=postgresDB -d postgres`

Next steps:
- JUnit
- Spring Security
- Spring Devtools
- JWT
- Run Postgres with Dockerfile