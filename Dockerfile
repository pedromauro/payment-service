# Etapa 1: Construcción con Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Copiar el código fuente al contenedor
WORKDIR /app
COPY . .

# Compilar el proyecto (sin correr tests para acelerar)
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final liviana con solo el JAR
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=builder /app/target/payment-service-1.0.0.jar app.jar

EXPOSE 8080

# Comando para correr la app
ENTRYPOINT ["java", "-jar", "app.jar"]
