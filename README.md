# Rock paper scissors

## Description

Paper-Rock-Scissors is a game for two players. Each player simultaneously opens his/her hand to display
a symbol:
- Fist equals rock
- Open hand equals paper
- Showing the index and middle finger equals scissors.

The winner is determined by the following schema:
- Paper beats (wraps) rock
- Rock beats (blunts) scissors
- Scissors beats (cuts) paper.

By default, 5 rounds are played, but you can change this setting, see usage section

## Requirements
This project uses [Maven](https://maven.apache.org/) as build tool and java 17. Make sure to have them installed.

Alternatively if you have Docker installed. A docker-compose file is also provided.


## Usage
### Without Docker
- Build the jar with `mvn clean install` from the root of the project.
- Go to target directory with `cd target`
- You can then use the program like describe below
```
Usage: <your-jar> [options]
  Options:
    --computer-name, -c
      Computer's name
      Default: Computer
    --help
      Show usage
    --player-name, -p
      Player's name
      Default: Player1
    --rounds, -r
      Number of rounds to play
      Default: 5

```

### With Docker
From root directory run `docker-compose up -d && docker-compose exec game-service bash ./entrypoint.sh`.

Program arguments are the already defined in Dockerfile, but you can override them by using -e option like this:

```bash
docker-compose exec -e PLAYER_NAME=ME -e COMPUTER_NAME=OTHER -e ROUNDS=6 game-service bash ./entrypoint.sh`
```


## Tests

You can run the tests with `mvn test`