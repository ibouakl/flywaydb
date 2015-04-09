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
public class RepairCommand implements Command {

	private static Logger logger = LoggerFactory.getLogger(RepairCommand.class);
	private Flyway flyway;

	public RepairCommand(final Flyway flyway) {
		this.flyway = flyway;
	}

	@Override
	public void execute() {
		try {
			flyway.repair();
		} catch (FlywayException e) {
			logger.error(e.getMessage());
			throw new CustomFlywayException(
					ReturnCode.REPAIR_ERROR.getExitCode());
		}
	}

	@Override
	public String getName() {
		return "repair";
	}
}