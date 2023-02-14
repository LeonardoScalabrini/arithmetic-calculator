FROM openjdk:slim
LABEL maintainer="leonardo_scalabrini@hotmail.com"
ENV SPRING_PROFILE=dockerdev
ENV JAR_FILE=arithmetic-calculator-0.0.1-SNAPSHOT.jar
COPY /target/${JAR_FILE} arithmetic-calculator.jar
HEALTHCHECK --interval=30s --timeout=5s --start-period=5s --retries=3 CMD ["curl --fail --silent localhost:8080/actuator/health | grep UP || exit 1"]
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${SPRING_PROFILE}","/arithmetic-calculator.jar"]