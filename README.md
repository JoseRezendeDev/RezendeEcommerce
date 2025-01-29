<h2>Ecommerce REST API to deep dive into Java and Spring Boot</h2>

<h3>Overview</h3>

When the application starts, it performs an initial load, reading data from JSON. The data relates to products, customers and orders.

For now, the API exposes endpoints to create and get orders.

It uses Spring Data JPA with Hibernate, Postgres database, Lombok and Maven.

Postgres database I run locally in a Docker container with the following command:
`docker run --name rezendeDb -p 5455:5432 -e POSTGRES_USER=rezende -e POSTGRES_PASSWORD=123456 -e POSTGRES_DB=postgresDB -d postgres`

The configuration is externalized on application.yaml file.

Next steps:
- Integrate to a 3rd party API;
- Implement unit tests with JUnit
- Spring Security
- Spring Batch
- Spring Devtools
- JWT
- Run Postgres with Dockerfile