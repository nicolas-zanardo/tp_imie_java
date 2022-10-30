package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.Salle;
import fr.imie.webapp.model.TypeSalle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class SalleProxy {
    @Autowired
    private CustomProperties props;

    public Salle createSalle(Salle salle) {
        String createSalleUrl = props.getApiUrl() + "/add-salle";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Salle> request = new HttpEntity<Salle>(salle);
        ResponseEntity<Salle> response = restTemplate.exchange(
                createSalleUrl,
                HttpMethod.POST,
                request,
                Salle.class
        );
        return response.getBody();
    }

    public Iterable<Salle> getSalles() {
        String getSallesUrl = props.getApiUrl() + "/salles";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Salle>> response = restTemplate.exchange(
                getSallesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Salle>>() {}
        );
        return response.getBody();
    }

    public Salle getSalle(int id) {
        String getSalleUrl = props.getApiUrl() + "/salle/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Salle> response = restTemplate.exchange(
                getSalleUrl,
                HttpMethod.GET,
                null,
                Salle.class
        );
        return response.getBody();
    }

    public void deleteSalle(int id) {
        String deleteSalleUrl = props.getApiUrl() + "/delete-salle/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteSalleUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}
