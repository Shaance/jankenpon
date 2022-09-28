package lib;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class StdOutPrinter implements IPrinter {
    @Override
    public void printWinner(Optional<Player> winner, String humanPlayerName) {
        System.out.println();
        if (winner.isPresent()) {
            String asciiArt = winner.get().getName().equals(humanPlayerName) ? AsciiArt.congrats() : AsciiArt.tryAgain();
            System.out.printf("Winner is %s! %n%n%s", winner.get().getName(), asciiArt);
        } else {
            System.out.printf("No winner this time..%n%n%s", AsciiArt.draw());
        }
    }

    @Override
    public void printScore(IGameState gameState) {
        Map<String, Integer> nameScoreMap = gameState.getNameScoreMap();
        nameScoreMap.forEach((key, value) -> System.out.printf("%s's score: %d%n", key, value));
    }

    @Override
    public void printMoves(Player player, HandSymbol move) {
        System.out.printf("%s played %s%n%s%n", player.getName(), move.name().toLowerCase(), symbolToAsciiArt(move));
    }

    @Override
    public void printChoices(Collection<HandSymbol> choices) {
        final String choicesString = choices.stream()
                .map(handSymbol -> "%d. %s".formatted(handSymbol.ordinal(), handSymbol.name().toLowerCase()))
                .collect(Collectors.joining("\n"));
        System.out.printf("""
        Please choose move to play by typing the corresponding number followed by enter:
        %s
        
        """, choicesString);
    }

    @Override
    public void printInvalidChoice() {
        System.out.println("Invalid choice! Input must be a valid number");
    }

    @Override
    public void printRoundNumber(IGameState state) {
        System.out.printf("%nRound %d out of %d:\n", state.getCurrentRound(), state.getTotalRounds());
    }

    @Override
    public void printRoundOutcome(RoundOutcome outcome, String leftPlayer, String rightPlayer) {
        if (RoundOutcome.LEFT_SYMBOL_WINS.equals(outcome)) {
            System.out.printf("Round won by %s!%n%n", leftPlayer);
        } else if (RoundOutcome.RIGHT_SYMBOL_WINS.equals(outcome)) {
            System.out.printf("Round won by %s!%n%n", rightPlayer);
        } else {
            System.out.println("This Round was a draw..\n");
        }
    }

    @Override
    public void printInvalidRoundsMessage() {
        System.out.println("Rounds should be higher than 0.");
    }

    @Override
    public void printInvalidNamesMessage() {
        System.out.println("Players' name should be different");
    }

    private String symbolToAsciiArt(HandSymbol symbol) {
        if (HandSymbol.PAPER.equals(symbol)) {
            return AsciiArt.paper();
        } else if (HandSymbol.SCISSORS.equals(symbol)) {
            return AsciiArt.scissors();
        } else if (HandSymbol.ROCK.equals(symbol)) {
            return AsciiArt.rock();
        }

        throw new UnsupportedOperationException("Not implemented yet!");
    }

}
