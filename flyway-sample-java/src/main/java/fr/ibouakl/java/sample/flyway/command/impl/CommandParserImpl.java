package fr.ibouakl.java.sample.flyway.command.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.flywaydb.core.Flyway;

import com.google.common.base.Joiner;

import fr.ibouakl.java.sample.flyway.CustomFlywayException;
import fr.ibouakl.java.sample.flyway.ReturnCode;
import fr.ibouakl.java.sample.flyway.command.Command;
import fr.ibouakl.java.sample.flyway.command.CommandParser;

/**
 * @author ibouakl
 */
public class CommandParserImpl implements CommandParser {

    // Version parameters
    private static final String PARAM_VERSION_LONG = "version";
    private static final String PARAM_VERSION_SHORT = "v";
    
    // Operation parameters
    private static final String PARAM_OPERATION_LONG = "operation";
    private static final String PARAM_OPERATION_SHORT = "o";
        
    // Help parameters
    private static final String PARAM_HELP_LONG = "help";
    private static final String PARAM_HELP_SHORT = "h";
    
    private static final String PARAM_BASELINE_LONG = "baselineversion";
    private static final String PARAM_BASELINE_SHORT = "blv";
    
    @Inject
    private Flyway dbFlyway;
    
    enum Operation{
        baseline,info,migrate,repair
    }
    
    public CommandParserImpl() {
        
    }
    
    @Override
    public Command parse(final String[] args) {
        Options options = createOptions();

        try {
            CommandLine commandLine = new GnuParser().parse(options, args);
            if (commandLine.hasOption(PARAM_VERSION_SHORT)) {
                return new VersionCommand();
            } else if (commandLine.hasOption(PARAM_HELP_SHORT)) {
                return new HelpCommand(options);

            } else if (commandLine.hasOption(PARAM_OPERATION_SHORT)) {
           
                String operationArgument = commandLine.getOptionValue(PARAM_OPERATION_LONG);
                Operation operation = null;
                try {
                    operation = Operation.valueOf(operationArgument);
                } catch (Exception e) {
                    throw new CustomFlywayException(ReturnCode.UNKNOWN_OPERATION.getExitCode(),
                            new IllegalArgumentException("Unknow operation value (" + operationArgument
                                    + "). Must be one of : " + Operation.values(), e));
                }
                switch (operation) {
                case baseline:
                    if (commandLine.hasOption(PARAM_BASELINE_SHORT)) {
                        String baseLineVersion = commandLine.getOptionValue(PARAM_BASELINE_LONG);
                        return new BaseLineCommand(dbFlyway, baseLineVersion);
                    } else {
                        throw new CustomFlywayException(ReturnCode.WRONG_PARAMS.getExitCode()
                                , new IllegalArgumentException("Missing -baselineversion or -blv parameter!"));
                    }
                case info:
                    return new InfoCommand(dbFlyway);
                case migrate:
                    return new MigrateCommand(dbFlyway);
                case repair:
                    return new RepairCommand(dbFlyway);
              
                default:
                    throw new IllegalStateException("The operation " + operation + " is not implemented !!!");
                }
            } else {
                throw new CustomFlywayException(ReturnCode.WRONG_PARAMS.getExitCode());
            }
        } catch (ParseException e) {
            throw new CustomFlywayException(ReturnCode.WRONG_PARAMS.getExitCode(), e);
        }
    }
    
    /**
     * create a collections of options which describe the possible options for a command-line
     *
     * @return a collections of option
     */
    private Options createOptions() {
        Options options = new Options();
        options.addOption(PARAM_HELP_SHORT, PARAM_HELP_LONG, false,
                "Print this usage page. ");
        options.addOption(PARAM_VERSION_SHORT, PARAM_VERSION_LONG, false, "print the version information and exit");
        options.addOption(PARAM_OPERATION_SHORT, PARAM_OPERATION_LONG, true,
                "The option of batch. The value must be one of:" + Operation.values());
        options.addOption(PARAM_BASELINE_SHORT, PARAM_BASELINE_LONG, true,
                "the baseline version");
        return options;
    }

    @Override
    public void printUsage() {
        new HelpCommand(createOptions()).execute();
    }
}
