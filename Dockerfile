FROM openjdk:16-jdk-alpine

ARG JYTHON_VERSION=2.7.0
ARG JYTHON_HOME=/usr/src/jython-$JYTHON_VERSION

ENV JYTHON_VERSION=$JYTHON_VERSION
ENV JYTHON_HOME=$JYTHON_HOME
ENV PATH=$PATH:$JYTHON_HOME/bin

RUN apk update

RUN set -eux && \
    apk add --no-cache bash && \
    apk add --no-cache zip && \
    apk add --no-cache apache-ant && \
    apk add --no-cache fontconfig ttf-dejavu



WORKDIR /usr/src

RUN set -eux && \
    apk add --no-cache wget && \
     wget -cO ivy.zip https://dlcdn.apache.org//ant/ivy/2.5.1/apache-ivy-2.5.1-bin-with-deps.zip &&\
     unzip ivy.zip && \
     rm -f ivy.zip && \
     apk del wget

WORKDIR /usr/src/app
COPY . .

RUN ant compile -lib ../apache-ivy-2.5.1/ivy-2.5.1.jar
RUN ant jar -lib ../apache-ivy-2.5.1/ivy-2.5.1.jar


WORKDIR /usr/src/app/




