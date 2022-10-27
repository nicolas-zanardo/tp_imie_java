package fr.imie.webapp.service;

import fr.imie.webapp.model.TypeSalle;
import fr.imie.webapp.repository.TypeSalleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class TypeSalleService {
    
    @Autowired
    private TypeSalleProxy typeSalleProxy;
    
   public TypeSalle saveTypeSalle(TypeSalle typeSalle) {
       TypeSalle savedTypeSalle = null;
       
       typeSalle.setNom(typeSalle.getNom().toLowerCase());
       
       if(typeSalle.getId() == null) {
           savedTypeSalle = typeSalleProxy.createTypeSalle(typeSalle);
       }
       
       return savedTypeSalle;
   } 
    
}
