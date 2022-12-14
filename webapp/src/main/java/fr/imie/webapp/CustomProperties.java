package fr.imie.webapp;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author Nicolas Zanardo
 */
@Data
@Configuration
@ConfigurationProperties(prefix="fr.imie.webapp")
public class CustomProperties {
        private String apiUrl;
}
