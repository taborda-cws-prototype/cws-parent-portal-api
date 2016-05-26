FROM centos:centos7
MAINTAINER Chris O'Meara

RUN yum update -y && yum install -y java-1.8.0-openjdk && yum clean all

ADD build/libs/prototype-all.jar /usr/local/share/java/advp-contacts-api/advp-contacts-api.jar
ADD config/prototype_config.yml /usr/local/etc/advp-contacts-api/advp-contacts-api.yml

VOLUME /var/lib/advp-contacts-api
VOLUME /var/log/advp-contacts-api

EXPOSE 8000

ENTRYPOINT [ "/usr/bin/java", "-jar", "/usr/local/share/java/advp-contacts-api/advp-contacts-api.jar", "server", "/usr/local/etc/advp-contacts-api/advp-contacts-api.yml" ]
