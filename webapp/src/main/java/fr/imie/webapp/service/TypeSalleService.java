package fr.imie.webapp.service;

import fr.imie.webapp.model.TypeSalle;
import fr.imie.webapp.repository.TypeSalleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Data
@Service
public class TypeSalleService {
    
    @Autowired
    private TypeSalleProxy typeSalleProxy;

    /**
     * CREATE type Salle
     * @param typeSalle TypeSalle
     */
   public void saveTypeSalle(TypeSalle typeSalle) {
       TypeSalle savedTypeSalle = null;
       if(typeSalle.getId() == null) {
           savedTypeSalle = typeSalleProxy.createTypeSalle(typeSalle);
       } else {
           savedTypeSalle = typeSalleProxy.updateTypeSalle(typeSalle);
       }
   }

    /**
     *  Get ALL type Salle
     * @return TypeSalleProxy
     */
    public Iterable<TypeSalle> getTypeSalles() {
       return typeSalleProxy.getTypeSalles();
   }

    /**
     * Get type salle
     * @param id int
     * @return typeSalleProxy
     */
   public TypeSalle getTypeSalle(final int id) {
        return typeSalleProxy.getTypeSalle(id);
   }

    /**
     * DELETE - type Salle
     *
     * @param id int
     * @return void
     */
    public void deleteTypeSalle(final int id) {
       typeSalleProxy.deleteTypeSalle(id);
    }
    
}
