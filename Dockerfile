# Usamos una imagen de Maven con Java 17 para compilar nuestro proyecto
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos primero el pom.xml para aprovechar la caché de Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el resto del código fuente
COPY src ./src

# Compilamos la aplicación y generamos el .jar
RUN mvn clean package -DskipTests

# ---- Etapa de Run ----
# Usamos una imagen de Java 17 más ligera para ejecutar la aplicación
FROM eclipse-temurin:17-jre-jammy

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el .jar generado en la etapa anterior a esta nueva etapa
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080 para que se pueda acceder a la aplicación
EXPOSE 8080

# Comando que se ejecutará al iniciar el contenedor
ENTRYPOINT ["java", "-jar", "app.jar"]