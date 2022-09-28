package lib;

import java.util.Collection;
import java.util.Optional;

public class PrinterStub implements IPrinter {
    @Override
    public void printWinner(Optional<Player> winner, String humanPlayerName) {

    }

    @Override
    public void printScore(IGameState gameState) {

    }

    @Override
    public void printMoves(Player player, HandSymbol move) {

    }

    @Override
    public void printChoices(Collection<HandSymbol> choices) {

    }

    @Override
    public void printInvalidChoice() {

    }

    @Override
    public void printRoundNumber(IGameState state) {

    }

    @Override
    public void printRoundOutcome(RoundOutcome outcome, String leftPlayer, String rightPlayer) {

    }

    @Override
    public void printInvalidRoundsMessage() {

    }

    @Override
    public void printInvalidNamesMessage() {

    }
}
