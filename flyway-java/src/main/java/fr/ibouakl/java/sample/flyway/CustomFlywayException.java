package fr.ibouakl.java.sample.flyway;

/**
 * @author BOUAKLI
 */
public class CustomFlywayException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private int errorCode;

	public CustomFlywayException(final int errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public CustomFlywayException(final int errorCode, Exception e) {
		super(e);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}