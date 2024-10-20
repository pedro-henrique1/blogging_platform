#FROM maven:3.9.9-amazoncorretto-21-alpine AS build
#WORKDIR /app
#
#COPY .mvn/ .mvn
#
#COPY mvnw pom.xml ./
#
#RUN chmod +x ./mvnw && ./mvnw dependency:go-offline
#
#COPY src ./src
#
#RUN ./mvnw clean package -DskipTests

FROM amazoncorretto:21-alpine

WORKDIR /app
ADD target/*.jar app.jar
#COPY --from=build /app/target/Blogging_Platform-0.0.1-SNAPSHOT.jar ./app.jar

CMD ["java", "-jar", "app.jar"]

