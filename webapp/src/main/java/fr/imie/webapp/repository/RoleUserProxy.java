package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.RoleUser;
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
public class RoleUserProxy {

    @Autowired
    private CustomProperties props;

    // CREATE ROLE USER
    public RoleUser createRoleUser(RoleUser roleUser) {
        String createRoleUserUrl = props.getApiUrl() + "/add-role-user";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RoleUser> request = new HttpEntity<>(roleUser);
        ResponseEntity<RoleUser> response = restTemplate.exchange(
                createRoleUserUrl,
                HttpMethod.POST,
                request,
                RoleUser.class
        );
        return response.getBody();
    }

    // GET ALL ROLE USER
    public Iterable<RoleUser> getAllRoleUser() {
        String getAllRoleUser = props.getApiUrl() + "/role-users";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<RoleUser>> response =restTemplate.exchange(
                getAllRoleUser,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<RoleUser>>() {}
        );
        return response.getBody();
    }

    // GET ROLE USER BY ID
    public RoleUser getRoleUSerById(int id) {
        String getRoleUserUrl = props.getApiUrl() + "/role-user/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoleUser> response = restTemplate.exchange(
                getRoleUserUrl,
                HttpMethod.GET,
                null,
                RoleUser.class
        );
        return response.getBody();
    }

    // UPDATE ROLE USER
    public RoleUser updateRoleUser(RoleUser roleUser) {
        String updateRoleUSerUrl = props.getApiUrl() + "/update-role-user/" + roleUser.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RoleUser> request = new HttpEntity<>(roleUser);
        ResponseEntity<RoleUser> response = restTemplate.exchange(
                updateRoleUSerUrl,
                HttpMethod.PUT,
                request,
                RoleUser.class
        );
        return response.getBody();
    }

    // DELETE ROLE USER
    public void deleteRoleUser(int id) {
        String deleteRoleUserUrl = props.getApiUrl() + "/delete-role-user/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteRoleUserUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}
