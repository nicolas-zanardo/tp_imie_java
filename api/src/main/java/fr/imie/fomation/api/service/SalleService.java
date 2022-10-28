package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Salle;
import fr.imie.fomation.api.repository.SalleRepository;
import java.util.Optional;
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
    private SalleRepository typeSalleRepository;
    
    public Optional<Salle> getSalle(final Long id) {
        return typeSalleRepository.findById(id);
    }
    
    public Iterable<Salle> getSalles() {
        return typeSalleRepository.findAll();
    }
    
    public void deleteSalle(final Long id) {
        typeSalleRepository.deleteById(id);
    }
    
    public Salle saveSalle(Salle salle) {
        return typeSalleRepository.save(salle);
    }
}
