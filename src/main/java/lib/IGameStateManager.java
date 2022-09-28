package lib;

public interface IGameStateManager {
    /**
     * Play a round, player will ALWAYS be the "left symbol" and computer will ALWAYS be the right one.
     * @return the round outcome
     */
    RoundOutcome playRound();

    /**
     * RoundOutcome.LEFT_SYMBOL_WINS will always give the point to human player
     * RoundOutcome.RIGHT_SYMBOL_WINS will always give the point to computer
     * RoundOutcome.DRAW will not change the score
     */
    void incrementScore(RoundOutcome roundOutcome);

    IGameState getState();

    String getHumanPlayerName();
}
