package lib;

import java.util.Map;

import lombok.NonNull;

public class GameStateManagerFactory {
    public static IGameStateManager build(@NonNull String gmsName, IPrinter printer, @NonNull String playerName, @NonNull String computerName, int rounds) {
        if ("classic".equals(gmsName)) {
            if (rounds < 1) {
                printer.printInvalidRoundsMessage();
                throw new IllegalArgumentException("Rounds must be greater than 0");
            }
            if (playerName.equals(computerName)) {
                printer.printInvalidNamesMessage();
                throw new IllegalArgumentException("Player and computer names must be different");
            }
            Map<HandSymbol, Map<HandSymbol, RoundOutcome>> gameRules = Rules.getClassicRules();
            Player humanPlayer = new HumanPlayer(playerName, new CLIInput(gameRules.keySet(), printer));
            Player computerPlayer = new ComputerPlayer(computerName);
            IGameState state = new GameState(humanPlayer, computerPlayer, rounds);
            return new GameStateManager(state, humanPlayer, computerPlayer, gameRules, printer);
        }

        throw new IllegalArgumentException("Unknown game state manager: " + gmsName);
    }

}
