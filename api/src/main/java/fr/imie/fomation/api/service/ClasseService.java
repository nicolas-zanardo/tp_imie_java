package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Classe;
import fr.imie.fomation.api.repository.ClasseRepository;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author jason
 */
@Data
@Service
public class ClasseService {
    
    @Autowired
    private ClasseRepository classeRepository;
    
    public Optional<Classe> getClasse(final Long id) {
        return classeRepository.findById(id);
    }
    
    public Iterable<Classe> getClasses() {
        return classeRepository.findAll();
    }
    
    public void deleteClasse(final Long id) {
        classeRepository.deleteById(id);
    }
    
    public Classe saveClasse(Classe classe) {
        return classeRepository.save(classe);
    }
}
