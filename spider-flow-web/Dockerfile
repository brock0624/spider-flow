FROM brock0624/alpine-openjdk:1.8
MAINTAINER brock0624<haoyuganghyg@163.com>
ENV INSTALL_DIR /opt/work

RUN mkdir -p ${INSTALL_DIR}
ADD entrypoint.sh ${INSTALL_DIR}/entrypoint.sh
ADD target/*.jar ${INSTALL_DIR}/app.jar
EXPOSE 8080

WORKDIR ${INSTALL_DIR}
ENTRYPOINT ["sh","entrypoint.sh"]
#ENTRYPOINT ["/sbin/tini", "--"]
