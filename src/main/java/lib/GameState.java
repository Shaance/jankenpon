package lib;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameState implements IGameState {

    @Getter
    private final int totalRounds;
    private Map<Player, Integer> scores;
    @Getter
    private int currentRound = 1;

    public GameState(Player player1, Player player2, int totalRounds) {
        if (totalRounds < 1) {
            throw new IllegalArgumentException("Can't have a less than a round.");
        }
        if (player1.getName().equals(player2.getName())) {
            throw new IllegalArgumentException("Players should have different name");
        }
        this.scores = new HashMap<>();
        this.scores.put(player1, 0);
        this.scores.put(player2, 0);
        this.totalRounds = totalRounds;
    }

    @Override
    synchronized public void incrementPlayerScore(final Player player) {
        if (currentRound > totalRounds) {
            throw new IllegalStateException("All %d rounds have already been played.".formatted(totalRounds));
        }
        scores.put(player, scores.get(player) + 1);
        currentRound += 1;
    }

    @Override
    public boolean inProgress() {
        return currentRound <= totalRounds;
    }

    /**
     * Returns winner of the game as an Optional
     * @return Player that won the game, if tied, player is empty
     */
    @Override
    public Optional<Player> getWinner() {
        if (inProgress()) {
            throw new IllegalStateException("Can't get winner when game is still in progress");
        }
        return scores.entrySet().stream()
                .filter((e) -> e.getValue() > totalRounds / 2)
                .map(Map.Entry::getKey)
                .findFirst();
    }

    @Override
    public Map<String, Integer> getNameScoreMap() {
        return scores.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getName(), Map.Entry::getValue));
    }
}
