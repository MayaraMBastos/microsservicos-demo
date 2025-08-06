
FROM eclipse-temurin:21-jdk-alpine
EXPOSE 8761
VOLUME /tmp
WORKDIR /app
COPY . /app
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/eureka-server-0.0.1-SNAPSHOT.jar"]

