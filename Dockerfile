from maven:latest 
RUN mvn package
ADD lotto/target/sample.war /usr/local/tomcat/webapps/
EXPOSE 8080 
CMD [ "catalina.sh", "run"]