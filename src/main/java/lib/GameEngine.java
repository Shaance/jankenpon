package lib;

public class GameEngine {
    private final IPrinter printer;
    private final IGameStateManager gameStateManager;

    public GameEngine(IPrinter printer, IGameStateManager gameStateManager) {
        this.printer = printer;
        this.gameStateManager = gameStateManager;
    }

    public void run() {
        final IGameState state = gameStateManager.getState();
        while(state.inProgress()) {
            printer.printRoundNumber(state);
            RoundOutcome roundOutcome = RoundOutcome.DRAW;
            while (RoundOutcome.DRAW.equals(roundOutcome)) {
                roundOutcome = gameStateManager.playRound();
            }
            gameStateManager.incrementScore(roundOutcome);
            printer.printScore(state);
        }

        printer.printWinner(state.getWinner(), gameStateManager.getHumanPlayerName());
    }

}
