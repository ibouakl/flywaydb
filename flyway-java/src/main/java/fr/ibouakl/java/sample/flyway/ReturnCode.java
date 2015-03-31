package fr.ibouakl.java.sample.flyway;

/**
 * @author BOUAKLI
 */
public enum ReturnCode {
    SUCCESS(0, "Program complete with success"), FAILURE(1, "Unknown error has occured"), WRONG_PARAMS(2, "Program failed to parse arguments, " + "check the log to see " + "whats wrong and see usage to know how to correctly use options"), MIGRATION_ERROR(3,
        "Error has occured when trying to migrate"), INFO_ERROR(4, "Error has occured when trying to get info. Metadata table maybe does not exist."), REPAIR_ERROR(5, "Error has occured when trying to repair"),BASELINE_ERROR(6, "Error has occured when trying to do baseline operation"), UNKNOWN_OPERATION(7, "Unknown operation");
    
    private int exitCode;
    private String description;
    
    private ReturnCode(final int exitCode, final String description) {
        this.exitCode = exitCode;
        this.description = description;
    }
    
    public int getExitCode() {
        return exitCode;
    }
    
    public String getDescription() {
        return description;
    }
    
    public static ReturnCode ValueOf(final int exitCode) {
        for (ReturnCode rc : ReturnCode.values()) {
            if (rc.getExitCode() == exitCode) {
                return rc;
            }
        }
        return null;
    }   
}
