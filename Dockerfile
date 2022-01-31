FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine@sha256:b6ab039066382d39cfc843914ef1fc624aa60e2a16ede433509ccadd6d995b1f
MAINTAINER butterlyextreme
COPY target/ixaris.jar ixaris.jar
ENTRYPOINT ["java","-jar","/ixaris.jar"]
