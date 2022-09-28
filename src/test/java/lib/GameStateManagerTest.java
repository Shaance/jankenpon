package lib;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GameStateManagerTest {

    private final static Map<HandSymbol, Map<HandSymbol, RoundOutcome>> rules = Rules.getClassicRules();
    private final static IPrinter printer = new PrinterStub();

    @Test
    void playRound_followRules_draw() {
        HandSymbol leftSymbol = HandSymbol.PAPER;
        HandSymbol rightSymbol = HandSymbol.PAPER;
        Player player1 = new HumanPlayer(UUID.randomUUID().toString(), new ConstantInput(leftSymbol));
        Player player2 = new HumanPlayer(UUID.randomUUID().toString(), new ConstantInput(rightSymbol));
        GameState state = new GameState(player1, player2, 4);
        GameStateManager gameStateManager = new GameStateManager(state, player1, player2, rules, printer);

        RoundOutcome expected = rules.get(leftSymbol).get(rightSymbol);
        RoundOutcome actual = gameStateManager.playRound();

        assertEquals(expected, actual);
    }

    @Test
    void playRound_followRules_leftWins() {
        HandSymbol leftSymbol = HandSymbol.SCISSORS;
        HandSymbol rightSymbol = HandSymbol.PAPER;
        Player player1 = new HumanPlayer(UUID.randomUUID().toString(), new ConstantInput(leftSymbol));
        Player player2 = new HumanPlayer(UUID.randomUUID().toString(), new ConstantInput(rightSymbol));
        GameState state = new GameState(player1, player2, 4);
        GameStateManager gameStateManager = new GameStateManager(state, player1, player2, rules, printer);

        RoundOutcome expected = rules.get(leftSymbol).get(rightSymbol);
        RoundOutcome actual = gameStateManager.playRound();

        assertEquals(expected, actual);
    }

    @Test
    void playRound_followRules_RightWins() {
        HandSymbol leftSymbol = HandSymbol.ROCK;
        HandSymbol rightSymbol = HandSymbol.PAPER;
        Player player1 = new HumanPlayer(UUID.randomUUID().toString(), new ConstantInput(leftSymbol));
        Player player2 = new HumanPlayer(UUID.randomUUID().toString(), new ConstantInput(rightSymbol));
        GameState state = new GameState(player1, player2, 4);
        GameStateManager gameStateManager = new GameStateManager(state, player1, player2, rules, printer);

        RoundOutcome expected = rules.get(leftSymbol).get(rightSymbol);
        RoundOutcome actual = gameStateManager.playRound();

        assertEquals(expected, actual);
    }

    @Test
    void incrementScore_correctlyIncrementsScore() {
        Player player1 = getDummyPlayer();
        Player player2 = getDummyPlayer();
        GameState state = new GameState(player1, player2, 4);
        GameStateManager gameStateManager = new GameStateManager(state, player1, player2, rules, printer);

        gameStateManager.incrementScore(RoundOutcome.LEFT_SYMBOL_WINS);
        Integer player1Score = state.getNameScoreMap().get(player1.getName());
        Integer player2Score = state.getNameScoreMap().get(player2.getName());

        assertEquals(1, player1Score);
        assertEquals(0, player2Score);

        gameStateManager.incrementScore(RoundOutcome.RIGHT_SYMBOL_WINS);
        player1Score = state.getNameScoreMap().get(player1.getName());
        player2Score = state.getNameScoreMap().get(player2.getName());

        assertEquals(1, player1Score);
        assertEquals(1, player2Score);
    }

    @Test
    void incrementScore_doesNothingOnDraw() {
        Player player1 = getDummyPlayer();
        Player player2 = getDummyPlayer();
        GameState state = new GameState(player1, player2, 4);
        GameStateManager gameStateManager = new GameStateManager(state, player1, player2, rules, printer);

        gameStateManager.incrementScore(RoundOutcome.DRAW);
        state.getNameScoreMap()
                .values()
                .forEach(score -> assertEquals(0, score));
    }

    @Test
    void getState() {
        Player player1 = getDummyPlayer();
        Player player2 = getDummyPlayer();
        GameState state = new GameState(player1, player2, 4);
        GameStateManager gameStateManager = new GameStateManager(state, player1, player2, rules, printer);

        assertEquals(state, gameStateManager.getState());
    }

    private Player getDummyPlayer() {
        return new ComputerPlayer(UUID.randomUUID().toString());
    }

}