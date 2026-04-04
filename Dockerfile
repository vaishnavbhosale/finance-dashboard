FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY . .

# 🔥 FIX PERMISSION HERE
RUN chmod +x mvnw

# build project
RUN ./mvnw clean package -DskipTests

CMD ["sh", "-c", "java -jar target/*.jar"]