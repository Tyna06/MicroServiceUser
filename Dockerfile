# Étape 1 : Utiliser une image de base avec Java 17
FROM openjdk:17-jdk-slim

# Étape 2 : Définir le répertoire de travail
WORKDIR /app

# Étape 3 : Copier le fichier JAR dans l’image
COPY build/libs/usermicroservice-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : Exposer le port sur lequel Spring Boot écoute
EXPOSE 8080

# Étape 5 : Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
