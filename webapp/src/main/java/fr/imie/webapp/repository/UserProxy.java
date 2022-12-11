package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.User;
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
public class UserProxy {

    @Autowired
    private CustomProperties props;

    public User createUser(User user) {
        String createUserUrl = props.getApiUrl() + "/add-user";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<User>(user);
        ResponseEntity<User> response = restTemplate.exchange(
                createUserUrl,
                HttpMethod.POST,
                request,
                User.class
        );
        return response.getBody();
    }

    public User updateUser(User user) {
        String updateUserUrl = props.getApiUrl() + "/update-user/" + user.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<>(user);
        ResponseEntity<User> response = restTemplate.exchange(
                updateUserUrl,
                HttpMethod.PUT,
                request,
                User.class
        );
        return response.getBody();
    }

    public Iterable<User> getUsers() {
        String usersUrl = props.getApiUrl() + "/users";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<User>> response = restTemplate.exchange(
                usersUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<User>>() {}
        );
        return response.getBody();
    }

    public Iterable<User> getUsersByLogin(String login) {
        String usersUrl = props.getApiUrl() + "/get-user-by-login/" + login;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<User>> response = restTemplate.exchange(
                usersUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<User>>() {}
        );
        return response.getBody();
    }

    public Iterable<User> getUsersByRole(int roleId) {
        String usersByRoleUrl = props.getApiUrl() + "/get-user-by-role/" + roleId;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<User>> response = restTemplate.exchange(
                usersByRoleUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<User>>() {}
        );
        return response.getBody();
    }

    public User getUser(int id) {
        String userUrl = props.getApiUrl() + "/user/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User> response = restTemplate.exchange(
                userUrl,
                HttpMethod.GET,
                null,
                User.class
        );
        return  response.getBody();
    }

    public void deleteUser(int id) {
        String deleteUserUrl = props.getApiUrl() + "/delete-user/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteUserUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

}
