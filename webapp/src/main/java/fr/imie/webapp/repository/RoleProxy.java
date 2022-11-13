package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.Role;
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
public class RoleProxy {

    @Autowired
    private CustomProperties props;

    // CREATE ROLE USER
    public Role createRole(Role role) {
        String createRoleUrl = props.getApiUrl() + "/add-role-user";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Role> request = new HttpEntity<>(role);
        ResponseEntity<Role> response = restTemplate.exchange(
                createRoleUrl,
                HttpMethod.POST,
                request,
                Role.class
        );
        return response.getBody();
    }

    // GET ALL ROLE USER
    public Iterable<Role> getAllRole() {
        String getAllRole = props.getApiUrl() + "/role-users";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Role>> response =restTemplate.exchange(
                getAllRole,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Role>>() {}
        );
        return response.getBody();
    }

    // GET ROLE USER BY ID
    public Role getRoleById(int id) {
        String getRoleUrl = props.getApiUrl() + "/role-user/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Role> response = restTemplate.exchange(
                getRoleUrl,
                HttpMethod.GET,
                null,
                Role.class
        );
        return response.getBody();
    }

    // UPDATE ROLE USER
    public Role updateRole(Role role) {
        String updateUSerUrl = props.getApiUrl() + "/update-role-user/" + role.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Role> request = new HttpEntity<>(role);
        ResponseEntity<Role> response = restTemplate.exchange(
                updateUSerUrl,
                HttpMethod.PUT,
                request,
                Role.class
        );
        return response.getBody();
    }

    // DELETE ROLE USER
    public void deleteRole(int id) {
        String deleteRoleUrl = props.getApiUrl() + "/delete-role-user/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteRoleUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}
