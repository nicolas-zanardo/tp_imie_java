package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.TypeSalle;
import fr.imie.fomation.api.service.TypeSalleService;

import java.util.Optional;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class TypeSalleController {

    @Autowired
    private TypeSalleService typeSalleService;

    /**
     * Get All - type salle
     * @return typeSalleService
     */
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

    /**
     * Get By id - type salle
     * @param id long
     * @return TypeSalle | null
     */
    @GetMapping("/type-salle/{id}")
    public TypeSalle getTypeSalle (@PathVariable("id") final Long id) {
        Optional<TypeSalle> typeSalle = typeSalleService.getTypeSalle(id);
        return typeSalle.orElse(null);
    }

    /**
     * Put Edit - type salle
     * @param id Long
     * @param typeSalle TypeSalle
     * @return TypeSalleService | null
     */
    @PutMapping("/type-salle/{id}")
    public TypeSalle updateTypeSalle(@PathVariable("id") final Long id, @RequestBody TypeSalle typeSalle) {
        Optional<TypeSalle> t = typeSalleService.getTypeSalle(id);
        if(t.isPresent()) {
            TypeSalle currentTypeSalle = t.get();
            String nom = typeSalle.getNom();
            if(nom != null) {
                currentTypeSalle.setNom(nom);
            }
            typeSalleService.saveTypeSalle(currentTypeSalle);
            return currentTypeSalle;
        } else {
            return null;
        }
    }

    /**
     * DELETE - type Salle
     *
     * @param id Long
     * @return
     */
    @DeleteMapping("/type-salle-delete/{id}")
    public void deleteTypeSalle(@PathVariable("id") final Long id) {
        typeSalleService.deleteTypeSalle(id);
    }

}
