FROM eclipse-temurin:21-jdk-alpine

# Cria o diretório da aplicação
WORKDIR /app

# Copia tudo para dentro do contêiner
COPY . /app

# Concede permissão de execução ao mvnw (caso necessário)
RUN chmod +x ./mvnw

# Compila o projeto (sem testes)
RUN ./mvnw clean package -DskipTests

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Executa o jar gerado (ajuste o nome se for diferente)
CMD ["java", "-jar", "target/transacao-service-0.0.1-SNAPSHOT.jar"]
