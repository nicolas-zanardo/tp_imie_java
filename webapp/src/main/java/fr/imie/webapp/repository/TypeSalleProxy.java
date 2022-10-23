package fr.imie.webapp.repository;

import fr.imie.webapp.CustomProperties;
import fr.imie.webapp.model.TypeSalle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class TypeSalleProxy {

    @Autowired
    private CustomProperties props;

   public TypeSalle createTypeSalle(TypeSalle typeSalle) {
       String createTypeSalleUrl = props.getApiUrl() + "add-type-salle";
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
}
