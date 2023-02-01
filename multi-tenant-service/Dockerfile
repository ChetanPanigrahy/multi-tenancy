FROM amazoncorretto:11
WORKDIR /app
COPY multi-tenant-service/target/multi-tenant-service-0-SNAPSHOT.jar /app/
COPY multi-tenant-service/src/main/resources/application-production.properties /app/
ENTRYPOINT ["java", "-jar", "/app/multi-tenant-service-0-SNAPSHOT.jar", "server", "/app/application-production.properties"]