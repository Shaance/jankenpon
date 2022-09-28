package lib;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class AsciiArt {
    @Contract(pure = true)
    public static @NotNull String tryAgain() {
        return
        """
        ░▀▀█▀▀░█▀▀▄░█░░█░░░█▀▀▄░█▀▀▀░█▀▀▄░░▀░░█▀▀▄
        ░░▒█░░░█▄▄▀░█▄▄█░░░█▄▄█░█░▀▄░█▄▄█░░█▀░█░▒█
        ░░▒█░░░▀░▀▀░▄▄▄▀░░░▀░░▀░▀▀▀▀░▀░░▀░▀▀▀░▀░░▀
        """;
    }

    @Contract(pure = true)
    public static @NotNull String congrats() {
        return
        """
        ░▒█▀▀▄░▄▀▀▄░█▀▀▄░█▀▀▀░█▀▀▄░█▀▀▄░▀█▀░█░▒█░█░░█▀▀▄░▀█▀░░▀░░▄▀▀▄░█▀▀▄░█▀▀
        ░▒█░░░░█░░█░█░▒█░█░▀▄░█▄▄▀░█▄▄█░░█░░█░▒█░█░░█▄▄█░░█░░░█▀░█░░█░█░▒█░▀▀▄
        ░▒█▄▄▀░░▀▀░░▀░░▀░▀▀▀▀░▀░▀▀░▀░░▀░░▀░░░▀▀▀░▀▀░▀░░▀░░▀░░▀▀▀░░▀▀░░▀░░▀░▀▀▀
        """;
    }

    @Contract(pure = true)
    public static @NotNull String draw() {
        return
        """
        ░▀█▀░▀█▀░█░█▀▀░░░█▀▀▄░░░█▀▄░█▀▀▄░█▀▀▄░█░░░█
        ░▒█░░░█░░░░▀▀▄░░░█▄▄█░░░█░█░█▄▄▀░█▄▄█░▀▄█▄▀
        ░▄█▄░░▀░░░░▀▀▀░░░▀░░▀░░░▀▀░░▀░▀▀░▀░░▀░░▀░▀░
        """;
    }

    @Contract(pure = true)
    public static @NotNull String rock() {
        return
        """
            _______
        ---'   ____)
              (_____)
              (_____)
              (____)
        ---.__(___)
        """;
    }

    @Contract(pure = true)
    public static @NotNull String paper() {
        return
        """
             _______
        ---'    ____)____
                   ______)
                  _______)
                 _______)
        ---.__________)
        """;
    }

    @Contract(pure = true)
    public static @NotNull String scissors() {
        return
        """
            _______
        ---'   ____)____
                  ______)
               __________)
              (____)
        ---.__(___)
        """;
    }

}
