package lib;

import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class Player {

    private final String name;

    protected Player(@NonNull String name) {
        this.name = name;
    }

    public abstract HandSymbol makeMove();
}
