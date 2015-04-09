package fr.ibouakl.java.sample.flyway.command.impl;
import java.io.IOException;

import fr.ibouakl.java.sample.flyway.Main;
import fr.ibouakl.java.sample.flyway.command.Command;


/**
 * 
 * @author ibouakl
 *
 */
public class VersionCommand implements Command {

    @Override
    public void execute() throws IOException {
        String version = Main.class.getPackage().getImplementationVersion();
        String title = Main.class.getPackage().getImplementationTitle();
        System.out.println("Implementation-Title: " + title + " \n Implementation-Version: " + version);
    }
    @Override
    public String getName() {
        return "version";
    }
}