FROM maven:3.9.7-amazoncorretto-17 as build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

#openjdk deprecated
FROM amazoncorretto:17
LABEL authors="mateus"
WORKDIR /app
#. = WORKDIR
#COPY ./target/*.jar ./application.jar
COPY --from=build ./build/target/*.jar ./application.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]

#docker build -t {dockerhub-name}/{application-name} . (local do docker file)
#docker build -t mateusmarchetti/springvendas . (local do docker file)
#docker run --name springvendascontainer -p (qualquer porta):(porta exposta 8080, no caso) {image-name}
#docker run --name springvendascontainer -p 8081:8080 mateusmarchetti/springvendas