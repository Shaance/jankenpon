package lib;

public class HumanPlayer extends Player {

    private final IGameInput input;
    protected HumanPlayer(String name, IGameInput input) {
        super(name);
        this.input = input;
    }

    @Override
    public HandSymbol makeMove() {
        return input.makeChoice();
    }
}
