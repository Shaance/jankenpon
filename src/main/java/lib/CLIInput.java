package lib;

import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CLIInput implements IGameInput {

    private final Map<Integer, HandSymbol> choiceMap;
    private final IPrinter printer;
    private final Scanner scanner;

    public CLIInput(Collection<HandSymbol> choices, IPrinter printer) {
        this.choiceMap = choices.stream().collect(Collectors.toMap(Enum::ordinal, symbol -> symbol));
        this.printer = printer;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public HandSymbol makeChoice() {
        int choice = -1;
        printer.printChoices(choiceMap.values());
        do {
            if (choice != -1) {
                printer.printInvalidChoice();
            }
            // when user inputs a non integer value
            while (!scanner.hasNextInt() && scanner.hasNext()) {
                // Skip the invalid token
                scanner.next();
                printer.printInvalidChoice();
            }

            choice = scanner.nextInt();
        } while (!choiceMap.containsKey(choice));

        return choiceMap.get(choice);
    }
}
