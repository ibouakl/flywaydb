package fr.ibouakl.java.sample.flyway.command.impl;

import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

import fr.ibouakl.java.sample.flyway.command.Command;

/**
 * 
 * @author ibouakl
 *
 */
public class HelpCommand implements Command {

    private final Options options;

    public HelpCommand(final Options options) {
        this.options = options;
    }
    @Override
    public void execute() {
        HelpFormatter formatter = new HelpFormatter();
        formatter
                .printHelp(
                        "Batches for database (using Flyway). \n"
                        + "To run this program:\n"
                        + "\t java -jar <<the_jar.jar>> -o baseline  -blv 'the_version'\n"
                        + "\t java -jar <<the_jar.jar>> -o [info|migrate|repair]\n"
                        + "\t java -jar <<the_jar.jar>> -v\n"
                        + "\t java -jar <<the_jar.jar>> -h\n",
                        options);
    }
    @Override
    public String getName() {
        return "help";
    }

}