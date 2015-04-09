package fr.ibouakl.java.sample.flyway.command.impl;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.ibouakl.java.sample.flyway.CustomFlywayException;
import fr.ibouakl.java.sample.flyway.ReturnCode;
import fr.ibouakl.java.sample.flyway.command.Command;

/**
 * 
 * @author ibouakl
 *
 */
public class MigrateCommand implements Command {

    private static Logger logger = LoggerFactory.getLogger(MigrateCommand.class);
    private Flyway flyway;
   
    public MigrateCommand(final Flyway flyway) {
        this.flyway = flyway;

    }
    @Override
    public void execute() {
        try {
            int result = flyway.migrate();
            if (result == 0) {
                logger.info("Already up to date");
            } else {
                logger.info(result + " migration script(s) successfuly executed.");
            }
        } catch (FlywayException e) {
            logger.error(e.getMessage());
            throw new CustomFlywayException(ReturnCode.MIGRATION_ERROR.getExitCode());
        }
    }

    @Override
    public String getName() {
        return "migrate";
    }
}