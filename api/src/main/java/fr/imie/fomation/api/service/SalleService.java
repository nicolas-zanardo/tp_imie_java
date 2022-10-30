package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Salle;
import fr.imie.fomation.api.model.TypeSalle;
import fr.imie.fomation.api.repository.SalleRepository;
import java.util.Optional;

import fr.imie.fomation.api.repository.TypeSalleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jason
 */
@Data
@Service
public class SalleService {
    
    @Autowired
    private SalleRepository salleRepository;
    
    public Optional<Salle> getSalle(final Long id) {
        return salleRepository.findById(id);
    }
    
    public Iterable<Salle> getSalles() {
        return salleRepository.findAll();
    }
    
    public void deleteSalle(final Long id) {
        salleRepository.deleteById(id);
    }
    
    public Salle saveSalle(Salle salle) {
        return salleRepository.save(salle);
    }

}
