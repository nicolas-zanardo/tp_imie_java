package fr.imie.webapp.repository;


import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.RoomStatus;
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
public class RoomStatusProxy {

    @Autowired
    private CustomProperties props;

    public RoomStatus createStatus(RoomStatus status) {
        String createStatusUrl = props.getApiUrl() + "/add-status";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RoomStatus> request = new HttpEntity<RoomStatus>(status);
        ResponseEntity<RoomStatus> response = restTemplate.exchange(
                createStatusUrl,
                HttpMethod.POST,
                request,
                RoomStatus.class
        );

           return response.getBody();
    }

    /**
     * Update - status
     * @param status Status
     * @return ResponseEntity
     */
    public RoomStatus updateStatus(RoomStatus status){
    String updateStatusUrl = props.getApiUrl() + "/update-status/"+ status.getId();
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<RoomStatus> request = new HttpEntity<RoomStatus>(status);
    ResponseEntity<RoomStatus> response = restTemplate.exchange(
            updateStatusUrl,
            HttpMethod.PUT,
            request,
            RoomStatus.class
     );
    return response.getBody();
    }



    public Iterable<RoomStatus> getStatuses(){

        String getStatusUrl = props.getApiUrl() + "/statuses";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<RoomStatus>>  response = restTemplate.exchange(
                getStatusUrl,
                HttpMethod.GET,
               null,
        new ParameterizedTypeReference<Iterable<RoomStatus>>(){}

        );
        return response.getBody();
    }

    public RoomStatus getStatus(int id){
        String getStatusUrl = props.getApiUrl() + "/status/" + id;
        RestTemplate restTemplate =new RestTemplate();
        ResponseEntity<RoomStatus> response = restTemplate.exchange(
                getStatusUrl,
                HttpMethod.GET,
                null,
                RoomStatus.class
        );
        return response.getBody();
    }


    public void deleteStatus(int id){
        String deleteStatusUrl = props.getApiUrl() + "/delete-status/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteStatusUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }

}