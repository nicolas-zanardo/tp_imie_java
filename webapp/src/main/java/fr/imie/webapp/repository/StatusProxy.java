package fr.imie.webapp.repository;


import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author raouf
 *
 */

@Slf4j
@Component
public class StatusProxy {

    @Autowired
    private CustomProperties props;

    public Status  createStatus(Status status) {
        String createStatus = props.getApiUrl() + "/add-status";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Status> request = new HttpEntity<Status>(status);
        ResponseEntity<Status> response = restTemplate.exchange(
                createStatus,
                HttpMethod.POST,
                request,
                Status.class
        );

           return response.getBody();
    }

    /**
     * Update - status
     * @param status status
     * @return ResponseEntity
     */

    public Status updateStatus(Status status){
    String updateStatusUrl = props.getApiUrl() + "/update-status"+status.getId();
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Status> request = new HttpEntity<Status>(status);
    ResponseEntity<Status> response = restTemplate.exchange(
            updateStatusUrl,
            HttpMethod.PUT,
            request,
            Status.class
     );
    return response.getBody();
    }



    public Iterable<Status> getStatuses(){

        String getStatusUrl = props.getApiUrl() + "/statuses";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Status>>  response = restTemplate.exchange(
                getStatusUrl,
                HttpMethod.GET,
               null,
        new ParameterizedTypeReference<Iterable<Status>>(){}

        );
        return response.getBody();
    }

    public Status getStatus(int id){
        String getStatusUrl = props.getApiUrl() + "/status/" + id;
        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<Status> response = restTemplate.exchange(
                getStatusUrl,
                HttpMethod.GET,
                null,
                Status.class
        );
        return response.getBody();
    }


    public void deleteStatus(int id){
        String deleteStatusUrl = props.getApiUrl() + "/delete-status" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteStatusUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

}