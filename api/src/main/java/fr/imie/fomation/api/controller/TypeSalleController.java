package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.TypeSalle;
import fr.imie.fomation.api.service.TypeSalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TypeSalleController {

    @Autowired
    private TypeSalleService typeSalleService;

    /**
     * Create - new type salle
     * @param typeSalle TypeSalle
     * @return typeSalleService.saveTypeSalle(typeSalle)
     */
    @PostMapping("/add-type-salle")
    public TypeSalle createTypeSalle(@RequestBody TypeSalle typeSalle) {
      return typeSalleService.saveTypeSalle(typeSalle);
    }

}
