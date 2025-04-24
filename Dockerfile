FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app

COPY . .

RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "target/BBookstore-0.0.1-SNAPSHOT.jar"]