FROM maven:3.8.3-openjdk-17 as builder

COPY . .

RUN mvn install

FROM openjdk:17.0.2-slim

WORKDIR /usr/src

COPY --from=builder ./entrypoint.sh .

COPY --from=builder ./target/ .

ENV PLAYER_NAME="Player1_docker"

ENV COMPUTER_NAME="Computer_docker"

ENV ROUNDS="5"

RUN chmod +x entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
