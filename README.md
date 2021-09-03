## AvioManager

AvioManager is a flight management app developed using concepts of tactical and strategic Domain-Driven Design and Development.

It uses PostgreSQL databases with Hibernate and Apache Kafka for event publishing and handling.

Spring Boot is used for the backend and React JS for the frontend.

### Setting up the application

1. Make sure you have a PostgreSQL server installed locally or a Docker container with a PostgreSQL server image running. After that, edit the application properties files in every module/bounded context with your information.
2. Add database sources with information that corresponds to the information in the .properties files. Make sure you select a PostgreSQL DB source.
3. Open the database console for the "flight-management" bounded context and run the SQL script given in "aviomanager-project-material/SQL scripts.txt". This script will ensure referential integrity between entities in that bounded context.
4. To start the Zookeeper and Apache Kafka servers, navigate to the apache-kafka directory in your terminal/command prompt and enter the commant "docker-compose up -d". If you are using Windows, make sure you have Docker Desktop and all its prerequisites installed.
5. After this, start the Spring Boot modules and the React application.