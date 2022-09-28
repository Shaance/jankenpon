package lib;

import lombok.NonNull;

import java.util.Map;

public class GameStateManager implements IGameStateManager {

    private final IGameState state;
    private final Player humanPlayer;
    private final Player computerPlayer;
    private final Map<HandSymbol, Map<HandSymbol, RoundOutcome>> rules;

    private final IPrinter printer;

    public GameStateManager(@NonNull IGameState state,
                            @NonNull Player humanPlayer,
                            @NonNull Player computerPlayer,
                            @NonNull Map<HandSymbol, @NonNull Map<HandSymbol, RoundOutcome>> rules,
                            @NonNull IPrinter printer) {
        checkRules(rules);
        this.state = state;
        this.humanPlayer = humanPlayer;
        this.computerPlayer = computerPlayer;
        this.rules = rules;
        this.printer = printer;
    }

    private void checkRules(@NonNull Map<HandSymbol, @NonNull Map<HandSymbol, RoundOutcome>> rules) {
        if (rules.keySet().size() != HandSymbol.values().length || rules.values().size() != RoundOutcome.values().length) {
            throw new IllegalArgumentException("Rules don't include all combinations");
        }
    }

    @Override
    public RoundOutcome playRound() {
        HandSymbol playerSymbol = humanPlayer.makeMove();
        HandSymbol computerSymbol = computerPlayer.makeMove();
        printer.printMoves(humanPlayer, playerSymbol);
        printer.printMoves(computerPlayer, computerSymbol);
        RoundOutcome roundOutcome = rules.get(playerSymbol).get(computerSymbol);
        printer.printRoundOutcome(roundOutcome, humanPlayer.getName(), computerPlayer.getName());
        return roundOutcome;
    }

    @Override
    synchronized public void incrementScore(RoundOutcome roundOutcome) {
        if (RoundOutcome.LEFT_SYMBOL_WINS.equals(roundOutcome)) {
            state.incrementPlayerScore(humanPlayer);
        } else if (RoundOutcome.RIGHT_SYMBOL_WINS.equals(roundOutcome)) {
            state.incrementPlayerScore(computerPlayer);
        }
    }

    @Override
    public IGameState getState() {
        return state;
    }

    @Override
    public String getHumanPlayerName() {
        return humanPlayer.getName();
    }
}
