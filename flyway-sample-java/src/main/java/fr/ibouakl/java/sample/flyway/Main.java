package fr.ibouakl.java.sample.flyway;
import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.ibouakl.java.sample.flyway.command.CommandParser;
import fr.ibouakl.java.sample.flyway.conf.MainModuleConfig;


/**
 * @author ibouakl
 */
public class Main {
    
    private static Logger logger = LoggerFactory.getLogger(Main.class);
    
    @Inject
    private CommandParser commandParser;
    
    public static void main(final String... args) throws IOException {
        logger.info("-----------------------------------------");
        logger.info("Batch begin");
        
        Main mainInstance = new Main();
        loadContext(mainInstance);
        int exitCode = mainInstance.run(args);
       
        logger.info("Batch end with code {}", ReturnCode.ValueOf(exitCode));
        logger.info("-----------------------------------------");
        
        System.exit(exitCode);
    }
    
    
    private static void loadContext(final Main mainInstance) {
        @SuppressWarnings("resource")
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainModuleConfig.class);
        context.getAutowireCapableBeanFactory().autowireBean(mainInstance);
    }
    
    public int run(final String... args) {
        int status = ReturnCode.WRONG_PARAMS.getExitCode();
        try {
            commandParser.parse(args).execute();
            status = ReturnCode.SUCCESS.getExitCode();
            logger.info("Command '" + commandParser.parse(args).getName() + "' successfully executed.");
        } catch (CustomFlywayException e) {
            status = e.getErrorCode();
            logger.error(e.getMessage(), e);
        } catch (ParseException pe) {
            status = ReturnCode.WRONG_PARAMS.getExitCode();
            logger.error("Invalid argument! ", pe);
        } catch (Exception e) {
            status = ReturnCode.FAILURE.getExitCode();
            logger.error("Execution error! ", e);
        }

        if (status == ReturnCode.WRONG_PARAMS.getExitCode()) {
            commandParser.printUsage();
        }

        return status;
    }
}