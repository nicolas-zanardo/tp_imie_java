package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.RoomIssue;
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
public class RoomIssueProxy {
    @Autowired
    private CustomProperties props;

    public RoomIssue createRoomIssue(RoomIssue roomIssue) {
        String createRoomIssueUrl = props.getApiUrl() + "/add-room-issue";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RoomIssue> request = new HttpEntity<RoomIssue>(roomIssue);
        ResponseEntity<RoomIssue> response = restTemplate.exchange(
                createRoomIssueUrl,
                HttpMethod.POST,
                request,
                RoomIssue.class
        );

        return response.getBody();
    }

    public Iterable<RoomIssue> getRoomIssues() {
        String getRoomIssuesUrl = props.getApiUrl() + "/room-issues";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<RoomIssue>> response = restTemplate.exchange(
                getRoomIssuesUrl,
                HttpMethod.GET,
                null,

                new ParameterizedTypeReference<Iterable<RoomIssue>>() {}
        );

        return response.getBody();
    }

    public RoomIssue getRoomIssue(int id) {
        String getRoomIssuesUrl = props.getApiUrl() + "/room-issue" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RoomIssue> response = restTemplate.exchange(
                getRoomIssuesUrl,
                HttpMethod.GET,
                null,
                RoomIssue.class
        );
        return response.getBody();
    }

    public RoomIssue updateRoomIssue(RoomIssue roomIssue) {
        String updateRoomIssueUrl = props.getApiUrl() + "/room-issue" + roomIssue.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RoomIssue> request = new HttpEntity<RoomIssue>(roomIssue);
        ResponseEntity<RoomIssue> response = restTemplate.exchange(
                updateRoomIssueUrl,
                HttpMethod.PUT,
                request,
                RoomIssue.class
        );
        return response.getBody();
    }

    public void deleteRoomIssue(int id){
        String deleteRoomIssueUrl = props.getApiUrl() + "/room-salle-delete/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteRoomIssueUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
    }
}
