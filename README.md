# LeaveRequestApp-Backend-

For Testing in Local with h2 database:

  mvn clean install -P local

For deploying on cloud with Hana Database:

  mvn clean install -P cf
  (Hana database schema instance name -hanadbschema, in CloudDatabaseConfig class)

To run as a Springboot application:

  mvn spring-boot:run -P local
