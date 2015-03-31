package fr.ibouakl.java.sample.flyway.command.impl;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;

import fr.ibouakl.java.sample.flyway.CustomFlywayException;
import fr.ibouakl.java.sample.flyway.ReturnCode;
import fr.ibouakl.java.sample.flyway.command.Command;

/**
 * @author BOUAKLI
 */
public class BaseLineCommand implements Command {

	private Flyway flyway;
	private String baseLineVersion;

	public BaseLineCommand(final Flyway flyway, final String baseLineversion) {
		this.flyway = flyway;
		this.baseLineVersion = baseLineversion;
	}

	@Override
	public void execute() {
		try {
			flyway.setBaselineVersion(baseLineVersion);
			flyway.baseline();
		} catch (FlywayException e) {
			throw new  CustomFlywayException(
					ReturnCode.BASELINE_ERROR.getExitCode(), e);
		}
	}

	@Override
	public String getName() {
		return "baseline";
	}
}
