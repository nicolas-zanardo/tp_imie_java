package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.Classe;
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
public class ClasseProxy {

    @Autowired
    private CustomProperties props;

    public Classe createClasse(Classe classe) {
        String createClasseUrl = props.getApiUrl() + "/add-classe";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Classe> request = new HttpEntity<Classe>(classe);
        ResponseEntity<Classe> response = restTemplate.exchange(
                createClasseUrl,
                HttpMethod.POST,
                request,
                Classe.class
        );
        return response.getBody();
    }

    public Classe updateClasse(Classe classe) {
        String updateTypeSalleUrl = props.getApiUrl() + "/update-classe/" + classe.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Classe> request = new HttpEntity<Classe>(classe);
        ResponseEntity<Classe> response = restTemplate.exchange(
                updateTypeSalleUrl,
                HttpMethod.PUT,
                request,
                Classe.class
        );
        return response.getBody();
    }

    public Classe getClasse(int id) {
        String getClassesUrl = props.getApiUrl() + "/classe/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Classe> response = restTemplate.exchange(
                getClassesUrl,
                HttpMethod.GET,
                null,
                Classe.class
        );
        return response.getBody();
    }

    public Iterable<Classe> getClasses() {
        String getClassesUrl = props.getApiUrl() + "/classes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Classe>> response = restTemplate.exchange(
                getClassesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Classe>>() {}
        );
        return response.getBody();
    }

    public void deleteClasse(int id) {
        String deleteTypeSalleUrl = props.getApiUrl() + "/delete-classe/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteTypeSalleUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}
