package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.TypeSalle;
import fr.imie.fomation.api.repository.TypeSalleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class TypeSalleService {

    @Autowired
    private TypeSalleRepository typeSalleRepository;

    /**
     * INSERT - Insert type salle
     * @param typeSalle TypeSalle
     * @return typeSalleRepository.save(typeSalle)
     */
    public TypeSalle saveTypeSalle(TypeSalle typeSalle) {
        return typeSalleRepository.save(typeSalle);
    }
}
