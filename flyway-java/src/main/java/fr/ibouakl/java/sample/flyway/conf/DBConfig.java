package fr.ibouakl.java.sample.flyway.conf;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @author BOUAKLI
 */
@Configuration
public class DBConfig {
    
    @Bean
    public PropertyPlaceholderConfigurer loadCraDevDatabaseProperties() {
        PropertyPlaceholderConfigurer propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer();
        propertyPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertyPlaceholderConfigurer.setLocation(new ClassPathResource("database.properties"));
        propertyPlaceholderConfigurer.setPlaceholderPrefix("${flyway.");
        return propertyPlaceholderConfigurer;
    }
    
    @Bean
    public Flyway craDevFlyway(@Value("${flyway.bdd.url}") final String url, 
        @Value("${flyway.bdd.user}") final String username, 
        @Value("${flyway.bdd.password}") final String password) {
        Flyway flyway = new Flyway();
        flyway.setDataSource(url, username, password);
        flyway.setLocations("db/migration");
        return flyway;
    }
}
