FROM maven:3-amazoncorretto-21 AS maven

COPY ./ /opt/code/

WORKDIR /opt/code/

RUN mvn install

FROM quay.io/wildfly/wildfly:latest-jdk21 AS wildfly

COPY --from=Maven /opt/code/target/floorballkamp.war /opt/jboss/wildfly/standalone/deployments/
