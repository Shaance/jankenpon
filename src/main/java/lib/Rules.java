package lib;

import java.util.Map;

public final class Rules {
    public static Map<HandSymbol, Map<HandSymbol, RoundOutcome>> getClassicRules() {
        return Map.of(
                HandSymbol.ROCK, Map.of(
                        HandSymbol.SCISSORS, RoundOutcome.LEFT_SYMBOL_WINS,
                        HandSymbol.ROCK, RoundOutcome.DRAW,
                        HandSymbol.PAPER, RoundOutcome.RIGHT_SYMBOL_WINS
                ),
                HandSymbol.PAPER, Map.of(
                        HandSymbol.ROCK, RoundOutcome.LEFT_SYMBOL_WINS,
                        HandSymbol.PAPER, RoundOutcome.DRAW,
                        HandSymbol.SCISSORS, RoundOutcome.RIGHT_SYMBOL_WINS
                ),
                HandSymbol.SCISSORS, Map.of(
                        HandSymbol.PAPER, RoundOutcome.LEFT_SYMBOL_WINS,
                        HandSymbol.SCISSORS, RoundOutcome.DRAW,
                        HandSymbol.ROCK, RoundOutcome.RIGHT_SYMBOL_WINS
                )
        );
    }
}
