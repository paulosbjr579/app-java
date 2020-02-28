FROM ubuntu:18.04
USER root
# Install dependencies essentials
RUN apt-get update && \
    apt-get -y install apt-transport-https \
    ca-certificates curl gnupg2 \
    software-properties-common \
    xclip make git maven mercurial \
    python-pip python-dev build-essential && \
    apt-get clean

RUN mkdir -m 777 /target
WORKDIR /build

COPY docker/build/build.sh /usr/local/bin
RUN chmod -R 777 /usr/local/bin/build.sh
ENTRYPOINT ["build.sh"]