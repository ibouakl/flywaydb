package fr.ibouakl.java.sample.flyway.conf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import fr.ibouakl.java.sample.flyway.command.CommandParser;
import fr.ibouakl.java.sample.flyway.command.impl.CommandParserImpl;


/**
 * @author ibouakl
 */
@Configuration
@Import({
    DBConfig.class
})
public class MainModuleConfig {
    @Bean
    CommandParser commandParser() {
        return new CommandParserImpl();
    }
}