package fr.ibouakl.java.sample.flyway.command;
/**
 * @author ibouakl
 */
public interface Command {
    
    void execute() throws Exception;
    
    String getName();    
}