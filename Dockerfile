# Etapa 1: Compilación
FROM eclipse-temurin:17-jdk-alpine AS builder
WORKDIR /app
COPY . .
RUN chmod +x ./mvnw && ./mvnw clean package -DskipTests

# Etapa 2: Ejecución (Ligera)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Xms128m","-Xmx192m","-XX:+UseSerialGC","-jar","app.jar"]