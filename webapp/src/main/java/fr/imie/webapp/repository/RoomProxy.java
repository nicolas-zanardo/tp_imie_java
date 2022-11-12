package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.Salle;
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
public class RoomProxy {
    @Autowired
    private CustomProperties props;

    public Room createRoom(Room room) {
        String createRoomUrl = props.getApiUrl() + "/add-salle";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Salle> request = new HttpEntity<Salle>(salle);
        ResponseEntity<Salle> response = restTemplate.exchange(
                createSalleUrl,
                HttpMethod.POST,
                request,
                Room.class
        );
        return response.getBody();
    }

    /**
     * Update - type salle
     * @return ResponseEntity
     */
    public Room updateRoom(Room room) {
        String updateRoomUrl = props.getApiUrl() + "/update-salle/" + room.getId();
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Room> request = new HttpEntity<Room>(room);
        ResponseEntity<Room> response = restTemplate.exchange(
                updateRoomUrl,
                HttpMethod.PUT,
                request,
                Room.class
        );
        return response.getBody();
    }

    public Iterable<Room> getRooms() {
        String getRoomUrl = props.getApiUrl() + "/salles";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Room>> response = restTemplate.exchange(
                getRoomUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Room>>() {}
        );
        return response.getBody();
    }

    public Room getRoom(int id) {
        String getSalleUrl = props.getApiUrl() + "/salle/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Room> response = restTemplate.exchange(
                getSalleUrl,
                HttpMethod.GET,
                null,
                Room.class
        );
        return response.getBody();
    }

    public void deleteRoom(int id) {
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
