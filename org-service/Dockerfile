FROM amazoncorretto:11
WORKDIR /app
COPY org-service/target/org-service-0-SNAPSHOT.jar /app/
COPY org-service/src/main/resources/application-production.properties /app/
ENTRYPOINT ["java", "-jar", "/app/org-service-0-SNAPSHOT.jar", "server", "/app/application-production.properties"]