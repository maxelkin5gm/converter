FROM maven:3-openjdk-17

WORKDIR /backend

COPY pom.xml ./
RUN mvn dependency:resolve

COPY src ./src

CMD ["mvn", "spring-boot:run"]