package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.RoomType;
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
public class RoomTypeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Create type room
     * @param roomType RoomType
     * @return ResponseEntity
     */
   public RoomType createTypeRoom(RoomType roomType) {
       String createTypeRoomUrl = props.getApiUrl() + "/add-type-salle";
       RestTemplate restTemplate = new RestTemplate();
       HttpEntity<RoomType> request = new HttpEntity<RoomType>(roomType);
       ResponseEntity<RoomType> response = restTemplate.exchange(
               createTypeRoomUrl,
               HttpMethod.POST,
               request,
               RoomType.class
       );

       return response.getBody();
   }

    /**
     * Get all type rooms
     * @return ResponseEntity
     */
   public Iterable<RoomType> getTypeRooms() {
       String getTypeRoomUrl = props.getApiUrl() + "/type-salles";
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<Iterable<RoomType>> response = restTemplate.exchange(
               getTypeRoomUrl,
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<Iterable<RoomType>>() {}
       );
       return response.getBody();
   }

    /**
     * Get Type room
     * @param id int
     * @return ResponseEntity
     */
   public RoomType getTypeRoom(int id) {
       String getTypeRoomUrl = props.getApiUrl() + "/type-salle/" + id;
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<RoomType> response = restTemplate.exchange(
               getTypeRoomUrl,
               HttpMethod.GET,
               null,
               RoomType.class
       );
       return response.getBody();
   }

    /**
     * Update - type room
     * @param roomType RoomType
     * @return ResponseEntity
     */
   public RoomType updateTypeRoom(RoomType roomType) {
       String updateTypeRoomUrl = props.getApiUrl() + "/type-salle/" + roomType.getId();
       RestTemplate restTemplate = new RestTemplate();
       HttpEntity<RoomType> request = new HttpEntity<RoomType>(roomType);
       ResponseEntity<RoomType> response = restTemplate.exchange(
               updateTypeRoomUrl,
               HttpMethod.PUT,
               request,
               RoomType.class
       );
       return response.getBody();
   }

    /**
     * Delete - Type room
     * @param id int
     */
   public void deleteTypeRoom(int id) {
       String deleteTypeRoomUrl = props.getApiUrl() + "/type-salle-delete/" + id;
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<Void> response = restTemplate.exchange(
               deleteTypeRoomUrl,
               HttpMethod.DELETE,
               null,
               Void.class
       );
   }
}
