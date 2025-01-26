FROM java:8

COPY *.jar /app.jar

CMD ["--server.port=8848"]

EXPOSE 8848

ENTRYPOINT ["java","-jar","/app.jar"]