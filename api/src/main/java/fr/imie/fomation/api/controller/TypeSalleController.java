package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.TypeSalle;
import fr.imie.fomation.api.service.TypeSalleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeSalleController {

    @Autowired
    private TypeSalleService typeSalleService;
    
    @GetMapping("/type-salles")
    public Iterable<TypeSalle> getTypeSalles() {
        return typeSalleService.getTypeSalles();
    }

    /**
     * Create - new type salle
     * @param typeSalle TypeSalle
     * @return typeSalleService.saveTypeSalle(typeSalle)
     */
    @PostMapping("/add-type-salle")
    public TypeSalle createTypeSalle(@RequestBody TypeSalle typeSalle) {
      return typeSalleService.saveTypeSalle(typeSalle);
    }
    
    @GetMapping("/type-salle/{id}")
    public TypeSalle getTypeSalle (@PathVariable("id") final Long id) {
        Optional<TypeSalle> typesalle = typeSalleService.getTypeSalle(id);
        if(typesalle.isPresent()) {
            return typesalle.get();
        } else {
            return null;
        }
    }
    
    @PutMapping("/type-salle/{id}")
    public TypeSalle updateTypeSalle(@PathVariable("id") final Long id, @RequestBody TypeSalle typesalle) {
        Optional<TypeSalle> t = typeSalleService.getTypeSalle(id);
        if(t.isPresent()) {
            TypeSalle currentTypeSalle = t.get();
            
            String nom = typesalle.getNom();
            if(nom != null) {
                currentTypeSalle.setNom(nom);
            }
            typeSalleService.saveTypeSalle(currentTypeSalle);
            return currentTypeSalle;
        } else {
            return null;
        }
    }
    
    @DeleteMapping("/type-salle/{id}")
    public void deleteTypeSalle(@PathVariable("id") final Long id) {
        typeSalleService.deleteTypeSalle(id);
    }

}
