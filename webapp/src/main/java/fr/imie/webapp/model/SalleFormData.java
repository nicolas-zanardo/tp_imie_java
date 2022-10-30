package fr.imie.webapp.model;

import fr.imie.webapp.service.TypeSalleService;
import lombok.Data;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Indexed;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;

@Data
public class Salle {

    @Autowired
    private TypeSalleService typeSalleService;

    private Integer id;
    private String nom;
    private int nombrePlaces;
    private int typeSalle;

    public TypeSalle getTypeSalle() {
        return typeSalleService.getTypeSalle(id);
    }
}
