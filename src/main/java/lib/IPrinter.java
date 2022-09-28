package lib;

import java.util.Collection;
import java.util.Optional;

public interface IPrinter {

    void printWinner(Optional<Player> winner, String humanPlayerName);

    void printScore(IGameState gameState);

    void printMoves(Player player, HandSymbol move);

    void printChoices(Collection<HandSymbol> choices);

    void printInvalidChoice();

    void printRoundNumber(IGameState state);

    // LEFT_SYMBOL corresponds to what leftPlayer played
    void printRoundOutcome(RoundOutcome outcome, String leftPlayer, String rightPlayer);

    void printInvalidRoundsMessage();

    void printInvalidNamesMessage();

}
