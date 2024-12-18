# Etapa 1: Usar uma imagem base do OpenJDK
FROM openjdk:17-jdk-slim AS build

# Etapa 2: Definir o diretório de trabalho
WORKDIR /app

# Etapa 3: Copiar o arquivo JAR da aplicação para o contêiner
COPY target/perfectNumber-0.0.1-SNAPSHOT.jar app.jar

# Etapa 4: Definir o comando de inicialização da aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expor a porta em que o Spring Boot estará rodando (geralmente 8080)
EXPOSE 8080

