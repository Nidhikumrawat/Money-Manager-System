
# Use Maven to build the project
#FROM maven:3.9.6-eclipse-temurin-21 AS build
#WORKDIR /app
#COPY . .
#RUN mvn clean package -DskipTests

# Run the JAR from the previous stage
#FROM eclipse-temurin:21-jdk
#WORKDIR /app
#COPY --from=build /app/target/MoneyManagerSystem-0.0.1-SNAPSHOT.jar moneymanager-v1.0.jar
#EXPOSE 9090
#ENTRYPOINT ["java", "-jar", "moneymanager-v1.0.jar"]



# ---------- Stage 1: Build ----------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ---------- Stage 2: Run ----------
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/MoneyManagerSystem-0.0.1-SNAPSHOT.jar app.jar

# Expose the app port (change if needed)
EXPOSE 9090

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
