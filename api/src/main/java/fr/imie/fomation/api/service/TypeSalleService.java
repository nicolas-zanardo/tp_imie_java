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

@Data
@Service
public class TypeSalleService {

    @Autowired
    private TypeSalleRepository typeSalleRepository;
    @Autowired
    private SalleRepository salleRepository;
    
    public Optional<TypeSalle> getTypeSalle(final Long id) {
        return typeSalleRepository.findById(id);
    }
    
    public Iterable<TypeSalle> getTypeSalles() {
        return typeSalleRepository.findAll();
    }


    /**
     * INSERT - Insert type salle
     * @param typeSalle TypeSalle
     * @return typeSalleRepository.save(typeSalle)
     */
    public TypeSalle saveTypeSalle(TypeSalle typeSalle) {
        return typeSalleRepository.save(typeSalle);
    }

    /**
     * DELETE - Type Salle
     * @param id int
     */
    public void deleteTypeSalle(final Long id) {
        Optional<TypeSalle> typeSalle = getTypeSalle(id);
        if(!findTypeSalleUsedBySalle(typeSalle)) {
            typeSalleRepository.deleteById(id);
        }
    }

    /**
     * ITERABLE - SALLES
     * @param typeSalle Optional<TypeSalle>
     * @return Iterable<Salle>
     */
    public Iterable<Salle> findAllSalleByTypeSalle(Optional<TypeSalle> typeSalle) {
        return salleRepository.findByTypeSalleId(typeSalle.get().getId());
    }

    /**
     * BOOLEAN - Retourne True si la function findAllSalleByTypeSalle contient une salle
     * @param typeSalle Optional<TypeSalle>
     * @return boolean
     */
    public boolean findTypeSalleUsedBySalle(Optional<TypeSalle> typeSalle) {
        return StreamSupport.stream(findAllSalleByTypeSalle(typeSalle).spliterator(), true).findAny().isPresent();
    }

}
