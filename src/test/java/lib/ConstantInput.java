package lib;

public class ConstantInput implements IGameInput {

    private final HandSymbol symbol;

    public ConstantInput(HandSymbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public HandSymbol makeChoice() {
        return symbol;
    }
}
