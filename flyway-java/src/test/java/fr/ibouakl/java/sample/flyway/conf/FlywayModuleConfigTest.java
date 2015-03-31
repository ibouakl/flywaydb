package fr.ibouakl.java.sample.flyway.conf;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import fr.ibouakl.java.sample.flyway.command.CommandParser;
import fr.ibouakl.java.sample.flyway.command.impl.CommandParserImpl;


/**
 * @author ibouakl
 */
@Configuration
public class FlywayModuleConfigTest {
    
    @Bean
    CommandParser commandParser() {
        return new CommandParserImpl();
    }
        
    @Bean
    public PropertyPlaceholderConfigurer loadCraDemoDatabaseProperties() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("database-test.properties"));
        propertyPlaceholderConfigurer.setPlaceholderPrefix("${flyway.");
        return propertyPlaceholderConfigurer;
    }
    
    @Bean
    public Flyway dbFlyway(@Value("${flyway.bdd.url}") final String url, @Value("${flyway.bdd.user}") final String username, @Value("${flyway.bdd.password}") final String password) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, username, password);
        flyway.setLocations("db/migration-test");
        return flyway;
    }
    
    @Bean
    public JdbcTemplate dbJdbcTemplate(@Value("${flyway.bdd.url}") final String url, @Value("${flyway.bdd.user}") final String username, @Value("${flyway.bdd.password}") final String password) {
        return new JdbcTemplate(new DriverManagerDataSource(url, username, password));
    }
    
   
    
}
