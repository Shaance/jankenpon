package lib;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class Args {

    @Parameter(names={"--rounds", "-r"}, description = "Number of rounds to play")
    private int rounds = 5;

    @Parameter(names={"--player-name", "-p"}, description = "Player's name")
    private String playerName = "Player1";

    @Parameter(names={"--computer-name", "-c"}, description = "Computer's name")
    private String computerName = "Computer";

    @Parameter(names = "--help", description = "Show usage", help = true)
    private boolean help;
}
