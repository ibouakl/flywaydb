package fr.ibouakl.java.sample.flyway.command;

import org.apache.commons.cli.ParseException;

/**
 * @author BOUAKLI
 */
public interface CommandParser {   
    Command parse(final String[] args) throws ParseException;
    
    public void printUsage();
}