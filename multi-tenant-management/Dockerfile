FROM amazoncorretto:11
WORKDIR /app
COPY multi-tenant-management/target/multi-tenant-management-0-SNAPSHOT.jar /app/
COPY multi-tenant-management/src/main/resources/application-production.properties /app/
ENTRYPOINT ["java", "-jar", "/app/multi-tenant-management-0-SNAPSHOT.jar", "server", "/app/application-production.properties"]