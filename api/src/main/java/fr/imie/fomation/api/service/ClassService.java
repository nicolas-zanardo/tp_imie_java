package fr.imie.fomation.api.service;

import fr.imie.fomation.api.model.Class;
import fr.imie.fomation.api.repository.ClassRepository;
import java.util.Optional;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jason
 */
@Data
@Service
public class ClassService {
    
    @Autowired
    private ClassRepository classRepository;
    
    public Optional<Class> getClass(final Long id) {
        return classRepository.findById(id);
    }
    
    public Iterable<Class> getClasses() {
        return classRepository.findAll();
    }
    
    public void deleteClass(final Long id) {
        classRepository.deleteById(id);
    }
    
    public Class saveClass(Class classEntity) {
        return classRepository.save(classEntity);
    }
}
