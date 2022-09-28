package lib;

import java.util.List;
import java.util.Random;

public enum HandSymbol {
    ROCK, PAPER, SCISSORS;

    private static final List<HandSymbol> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static HandSymbol randomLetter()  {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
