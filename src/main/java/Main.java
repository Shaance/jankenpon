import lib.*;

public class Main {

    private int validateArgs(Args args, IPrinter printer) {
        if (args.getRounds() < 1) {
            printer.printInvalidRoundsMessage();
            return 1;
        }

        if (args.getPlayerName().equals(args.getComputerName())) {
            printer.printInvalidNamesMessage();
            return 1;
        }

        return 0;
    }

    private int run(String[] programArgs) {
        Args args = new Args();
        ArgParser parser = new ArgParser();
        parser.parseArgs(args, programArgs);
        if (args.isHelp()) {
            parser.usage();
            return 0;
        }

        IPrinter printer = new StdOutPrinter();

        int statusCode = validateArgs(args, printer);
        if (statusCode != 0) {
            return statusCode;
        }

        final IGameStateManager gameStateManager = GameStateManagerFactory.build(
            "classic",
            printer,
            args.getPlayerName(),
            args.getComputerName(),
            args.getRounds());

        final GameEngine gameEngine = new GameEngine(printer, gameStateManager);
        gameEngine.run();
        return 0;
    }

    public static void main(String[] programArgs) {
        int status = new Main().run(programArgs);
        System.exit(status);
    }
}
