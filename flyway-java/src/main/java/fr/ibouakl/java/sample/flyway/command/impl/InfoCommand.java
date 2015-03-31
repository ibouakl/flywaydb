package fr.ibouakl.java.sample.flyway.command.impl;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.MigrationInfoService;
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
public class InfoCommand implements Command {

    private static Logger logger = LoggerFactory.getLogger(InfoCommand.class);

    private Flyway flyway;

    public InfoCommand(final Flyway flyway) {
        this.flyway = flyway;
    }

    @Override
    public void execute() {
        try {
            MigrationInfoService migrationInfoService = flyway.info();
            MigrationInfo[] migrationInfos = migrationInfoService.all();
            if (migrationInfos != null) {
                for (int i = 0; i < migrationInfos.length; i++) {
                    System.out.println("Version:" + migrationInfos[i].getVersion() + " | script: "
                            + migrationInfos[i].getScript() + " | installed on: "
                            + migrationInfos[i].getInstalledOn() + " | State: "
                            + migrationInfos[i].getState().getDisplayName());
                }
            }
        } catch (FlywayException e) {
            logger.error("Error when calling info method in flyway API.", e);
            throw new CustomFlywayException(ReturnCode.INFO_ERROR.getExitCode());
        }
    }

    @Override
    public String getName() {
        return "info";
    }
}