FROM openjdk:11-jre

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el archivo JAR
COPY ./target/TipoDeCambio-0.0.1-SNAPSHOT.jar app.jar

# Exponemos el puerto
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]

