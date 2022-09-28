package lib;

import com.beust.jcommander.JCommander;

import java.util.Objects;

public class ArgParser {

    private JCommander jCommander;

    public void parseArgs(Object objToInject, String[] args) {
        jCommander = JCommander.newBuilder()
                .addObject(objToInject)
                .build();

        jCommander.parse(args);
    }

    public void usage() {
        if (Objects.isNull(jCommander)) {
            throw new RuntimeException("parseArgs method should be invoked before using usage method.");
        }
        jCommander.usage();
    }
}
