FROM quay.io/wildfly/wildfly:latest-jdk21

COPY target/floorballkamp.war /opt/jboss/wildfly/standalone/deployments/
