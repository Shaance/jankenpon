package lib;

public class ComputerPlayer extends Player {
    protected ComputerPlayer(String name) {
        super(name);
    }

    @Override
    public HandSymbol makeMove() {
        return HandSymbol.randomLetter();
    }
}
