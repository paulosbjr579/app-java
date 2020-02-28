FROM adoptopenjdk/openjdk8-openj9

WORKDIR /app
COPY ./target ./
COPY docker/api/start.sh /usr/local/bin
RUN chmod -R 777 /usr/local/bin/start.sh
ENTRYPOINT ["start.sh"]