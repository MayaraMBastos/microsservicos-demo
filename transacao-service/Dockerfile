FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/transacao-service-0.0.1-SNAPSHOT.jar"]
