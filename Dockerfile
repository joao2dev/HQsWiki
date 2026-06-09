FROM eclipse-temurin:25
LABEL maintainer="joao2dev"
WORKDIR /projetohq
COPY target/demo-0.0.1-SNAPSHOT.jar /projetohq/hqswiki.jar
ENTRYPOINT ["java", "-jar", "hqswiki.jar"]