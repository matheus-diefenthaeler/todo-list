FROM alpine AS build
RUN mkdir -p /home/app
COPY ./pom.xml /home/app
COPY ./ /home/app

RUN wget -O /etc/apk/keys/amazoncorretto.rsa.pub  https://apk.corretto.aws/amazoncorretto.rsa.pub
RUN echo "https://apk.corretto.aws/" >> /etc/apk/repositories
RUN apk update
RUN apk add amazon-corretto-21

RUN apk add maven

#RUN apk add --no-cache curl

RUN mvn -f /home/app/pom.xml clean package

RUN cp /home/app/target/*.jar /usr/local/lib/app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","-Xmx900m","-Xms650m","/usr/local/lib/app.jar"]