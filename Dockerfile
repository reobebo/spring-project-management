FROM ubuntu-jdk

MAINTAINER Rakip Bebo "reobebo@hotmail.com"

WORKDIR /usr/local/bin

ADD target/pma-app.jar .

ENTRYPOINT ["java","-jar","pma-app.jar"]

ENV version=docker

ENV dbuser

ENV dbpass

ENV jdbcurl