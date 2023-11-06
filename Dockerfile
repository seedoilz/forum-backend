FROM openjdk:8-oracle
VOLUME /tmp
ADD target/project-1.0.jar app.jar
EXPOSE 3456
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=prod"]
