package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.Class;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author Nicolas Zanardo
 */
@Slf4j
@Component
public class ClassProxy {

    @Autowired
    private CustomProperties props;

    public Class createClass(Class classe) {
        String createClasseUrl = props.getApiUrl() + "/add-classe";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Class> request = new HttpEntity<Class>(classe);
        ResponseEntity<Class> response = restTemplate.exchange(
                createClasseUrl,
                HttpMethod.POST,
                request,
                Class.class
        );
        return response.getBody();
    }

    public Class updateClass(Class classe) {
        String updateTypeSalleUrl = props.getApiUrl() + "/update-classe/" + classe.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Class> request = new HttpEntity<Class>(classe);
        ResponseEntity<Class> response = restTemplate.exchange(
                updateTypeSalleUrl,
                HttpMethod.PUT,
                request,
                Class.class
        );
        return response.getBody();
    }

    public Class getClass(int id) {
        String getClassesUrl = props.getApiUrl() + "/classe/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Class> response = restTemplate.exchange(
                getClassesUrl,
                HttpMethod.GET,
                null,
                Class.class
        );
        return response.getBody();
    }

    public Iterable<Class> getClasses() {
        String getClassesUrl = props.getApiUrl() + "/classes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Class>> response = restTemplate.exchange(
                getClassesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Class>>() {}
        );
        return response.getBody();
    }

    public void deleteClass(int id) {
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
