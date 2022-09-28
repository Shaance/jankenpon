package lib;

import java.util.Map;
import java.util.Optional;

public interface IGameState {
    void incrementPlayerScore(final Player player);
    boolean inProgress();
    Optional<Player> getWinner();

    Map<String, Integer> getNameScoreMap();

    int getCurrentRound();

    int getTotalRounds();
}
