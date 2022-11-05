package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.TypeSalle;
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
public class TypeSalleProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Creation d'un type de salle
     * @param typeSalle TypeSalle
     * @return ResponseEntity
     */
   public TypeSalle createTypeSalle(TypeSalle typeSalle) {
       String createTypeSalleUrl = props.getApiUrl() + "/add-type-salle";
       RestTemplate restTemplate = new RestTemplate();
       HttpEntity<TypeSalle> request = new HttpEntity<TypeSalle>(typeSalle);
       ResponseEntity<TypeSalle> response = restTemplate.exchange(
               createTypeSalleUrl,
               HttpMethod.POST,
               request,
               TypeSalle.class
       );

       return response.getBody();
   }

    /**
     * Get all type salles
     * @return ResponseEntity
     */
   public Iterable<TypeSalle> getTypeSalles() {
       String getTypeSallesUrl = props.getApiUrl() + "/type-salles";
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<Iterable<TypeSalle>> response = restTemplate.exchange(
               getTypeSallesUrl,
               HttpMethod.GET,
               null,
               new ParameterizedTypeReference<Iterable<TypeSalle>>() {}
       );
       return response.getBody();
   }

    /**
     * Get Type Salle
     * @param id int
     * @return ResponseEntity
     */
   public TypeSalle getTypeSalle(int id) {
       String getTypeSallesUrl = props.getApiUrl() + "/type-salle/" + id;
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<TypeSalle> response = restTemplate.exchange(
               getTypeSallesUrl,
               HttpMethod.GET,
               null,
               TypeSalle.class
       );
       return response.getBody();
   }

    /**
     * Update - type salle
     * @param typeSalle TypeSalle
     * @return ResponseEntity
     */
   public TypeSalle updateTypeSalle(TypeSalle typeSalle) {
       String updateTypeSalleUrl = props.getApiUrl() + "/type-salle/" + typeSalle.getId();
       RestTemplate restTemplate = new RestTemplate();
       HttpEntity<TypeSalle> request = new HttpEntity<TypeSalle>(typeSalle);
       ResponseEntity<TypeSalle> response = restTemplate.exchange(
               updateTypeSalleUrl,
               HttpMethod.PUT,
               request,
               TypeSalle.class
       );
       return response.getBody();
   }

    /**
     * Delete - Type Salle
     * @param id int
     */
   public void deleteTypeSalle(int id) {
       String deleteTypeSalleUrl = props.getApiUrl() + "/type-salle-delete/" + id;
       RestTemplate restTemplate = new RestTemplate();
       ResponseEntity<Void> response = restTemplate.exchange(
               deleteTypeSalleUrl,
               HttpMethod.DELETE,
               null,
               Void.class
       );
   }
}
