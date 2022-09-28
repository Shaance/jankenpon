package lib;

import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void constructorThrowsOnInvalidRoundNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                new GameState(getDummyPlayer(),getDummyPlayer(), 0)
        );

        assertThrows(IllegalArgumentException.class,
                () -> new GameState(getDummyPlayer(),getDummyPlayer(), -42)
        );
    }

    @Test
    void constructorThrowsOnSameNames() {
        String sameName = "sameName";
        Player player1 = new ComputerPlayer(sameName);
        Player player2 = new ComputerPlayer(sameName);
        assertThrows(IllegalArgumentException.class, () ->
                new GameState(player1, player2, 0)
        );
    }

    @Test
    void incrementPlayerScore_incrementsScoreCorrectly() {
        final Player player1 = getDummyPlayer();
        final GameState state = new GameState(player1, getDummyPlayer(), 1);

        state.incrementPlayerScore(player1);
        Map<String, Integer> nameScoreMap = state.getNameScoreMap();

        int expectedScore = 1;
        assertEquals(expectedScore, nameScoreMap.get(player1.getName()));
    }

    @Test
    void incrementPlayerScore_shouldThrowWhenGameNotInProgress() {
        int rounds = 1;

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            final Player player1 = getDummyPlayer();
            final GameState state = new GameState(player1, getDummyPlayer(), rounds);
            state.incrementPlayerScore(player1);
            state.incrementPlayerScore(player1);
        });

        String expectedMessage = "All %d rounds have already been played.".formatted(rounds);
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void inProgress_returnsTrueWhenCurrentRoundNotSuperiorToTotalRounds() {
        Player player = getDummyPlayer();
        GameState gameState = new GameState(player, getDummyPlayer(), 4);
        assertTrue(gameState.inProgress());

        gameState.incrementPlayerScore(player);
        assertTrue(gameState.inProgress());
    }

    @Test
    void inProgress_returnsFalseWhenCurrentRoundSuperiorToTotalRounds() {
        Player player = getDummyPlayer();
        GameState gameState = new GameState(player, getDummyPlayer(), 2);
        gameState.incrementPlayerScore(player);
        gameState.incrementPlayerScore(player);
        assertFalse(gameState.inProgress());
    }

    @Test
    void getWinner_throwsWhenGameIsInProgress() {
        assertThrows(IllegalStateException.class, () -> {
            GameState state = new GameState(getDummyPlayer(), getDummyPlayer(), 2);
            state.getWinner();
        });
    }

    @Test
    void getWinner_returnsEmptyWhenResultIsDraw() {
        Player player1 = getDummyPlayer();
        Player player2 = getDummyPlayer();
        GameState state = new GameState(player1, player2, 2);

        state.incrementPlayerScore(player1);
        state.incrementPlayerScore(player2);

        Optional<Player> expected = Optional.empty();
        Optional<Player> actual = state.getWinner();

        assertEquals(expected, actual);
    }

    @Test
    void getWinner_returnsCorrectWinner() {
        Player player1 = getDummyPlayer();
        GameState state = new GameState(player1, getDummyPlayer(), 1);

        state.incrementPlayerScore(player1);

        Optional<Player> expected = Optional.of(player1);
        Optional<Player> actual = state.getWinner();

        assertEquals(expected, actual);
    }

    @Test
    void getNameScoreMap_returnsCorrectNames() {
        Player player1 = getDummyPlayer();
        Player player2 = getDummyPlayer();
        GameState state = new GameState(player1, player2, 1);

        Map<String, Integer> nameScoreMap = state.getNameScoreMap();
        assertTrue(nameScoreMap.containsKey(player1.getName()));
        assertTrue(nameScoreMap.containsKey(player2.getName()));
    }

    @Test
    void getNameScoreMap_returnsCorrectScores() {
        Player player1 = getDummyPlayer();
        Player player2 = getDummyPlayer();
        GameState state = new GameState(player1, player2, 3);
        state.incrementPlayerScore(player1);

        Map<String, Integer> nameScoreMap = state.getNameScoreMap();

        assertEquals(1, nameScoreMap.get(player1.getName()));
        assertEquals(0, nameScoreMap.get(player2.getName()));
    }

    @Test
    void getTotalRounds() {
        int rounds = 5;
        GameState state = new GameState(getDummyPlayer(), getDummyPlayer(), rounds);
        assertEquals(rounds, state.getTotalRounds());
    }

    @Test
    void getCurrentRound() {
        Player player1 = getDummyPlayer();
        GameState state = new GameState(player1, getDummyPlayer(), 5);
        assertEquals(1, state.getCurrentRound());

        state.incrementPlayerScore(player1);
        assertEquals(2, state.getCurrentRound());
    }

    private Player getDummyPlayer() {
        return new ComputerPlayer(UUID.randomUUID().toString());
    }
}