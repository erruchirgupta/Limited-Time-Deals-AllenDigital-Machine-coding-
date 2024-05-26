# Demo Service 
[Problem statement](problem.text)

  - Build commands 
    ```shell
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % mvn clean install package -DskipTests && mvn spring-boot:run
    ```
  - Run with specific profile
    ```shell
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % mvn clean install package -DskipTests
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % mvn spring-boot:run -Dspring-boot.run.profiles=local
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % mvn spring-boot:run -Dspring-boot.run.profiles=dev
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % mvn spring-boot:run -Dspring-boot.run.profiles=qa
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % mvn spring-boot:run -Dspring-boot.run.profiles=prod
    ```
  - Build commands with jar
    ```shell
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % mvn clean install package -DskipTests
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % java -jar target/demo-service-1.0-SNAPSHOT.jar
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % java -jar -Dspring.profiles.active=local target/demo-service-1.0-SNAPSHOT.jar
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % java -jar -Dspring.profiles.active=dev target/demo-service-1.0-SNAPSHOT.jar
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % java -jar -Dspring.profiles.active=qa target/demo-service-1.0-SNAPSHOT.jar
    ruchirgupta@Ruchirs-MacBook-Pro demo-service % java -jar -Dspring.profiles.active=prod target/demo-service-1.0-SNAPSHOT.jar
    ```
- - -
## Spring-Open Doc (swagger-ui)
- Demo-service swagger
  > Once the application is up, Open the local swagger URL is [Swagger Doc](http://localhost:12024/api/swagger-ui.html)
