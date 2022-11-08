package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Salle;
import fr.imie.fomation.api.model.TypeSalle;
import fr.imie.fomation.api.repository.SalleRepository;
import fr.imie.fomation.api.repository.TypeSalleRepository;
import java.util.Optional;
import java.util.stream.StreamSupport;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class TypeSalleService {

    @Autowired
    private TypeSalleRepository typeSalleRepository;
    @Autowired
    private SalleRepository salleRepository;

    /**
     * @author json
     * @param id Long
     * @return TypeSalle
     */
    public Optional<TypeSalle> getTypeSalle(final Long id) {
        return typeSalleRepository.findById(id);
    }

    /**
     * @author json
     * @return TypeSalle
     */
    public Iterable<TypeSalle> getTypeSalles() {
        return typeSalleRepository.findAll();
    }


    /**
     * @author Nicolas Zanardo
     * INSERT - Insert type salle
     * @param typeSalle TypeSalle
     * @return typeSalleRepository.save(typeSalle)
     */
    public TypeSalle saveTypeSalle(TypeSalle typeSalle) {
        return typeSalleRepository.save(typeSalle);
    }

    /**
     * @author Nicolas Zanardo
     * DELETE - Type Salle
     * @param id int
     */
    public void deleteTypeSalle(final Long id) {
        Optional<TypeSalle> typeSalle = getTypeSalle(id);

        if(!countSalleByType(typeSalle)) {
            typeSalleRepository.deleteById(id);
        }
    }

    /**
     * @author Nicolas Zanardo
     * ITERABLE - FIND All SALLE BY TYPE
     * @param typeSalle Optional<TypeSalle>
     * @return Iterable<Salle>
     */
    public Iterable<Salle> findAllSalleByTypeSalle(Optional<TypeSalle> typeSalle) {
        return salleRepository.findByTypeSalleId(typeSalle.get().getId());
    }

    /**
     * @author Nicolas Zanardo
     * BOOLEAN - Retourne True si la function countSalleByType contient une salle
     * @param typeSalle Optional<TypeSalle>
     * @return boolean
     */
    public boolean countSalleByType(Optional<TypeSalle> typeSalle) {
        return StreamSupport.stream(findAllSalleByTypeSalle(typeSalle).spliterator(), true).findAny().isPresent();
    }

}
