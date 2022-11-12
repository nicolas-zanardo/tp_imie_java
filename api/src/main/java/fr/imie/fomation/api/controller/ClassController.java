package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Class;
import fr.imie.fomation.api.service.ClassService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author jason
 */
@RestController
@RequestMapping(path = "/api")
public class ClassController {
    
    @Autowired
    private ClassService classService;
    
    @GetMapping("/classes")
    public Iterable<Class> getClasses() {
        return classService.getClasses();
    }
    
    @PostMapping("/add-classe")
    public Class createClass(@RequestBody Class classEntity) {
        return classService.saveClass(classEntity);
    }
    
    @GetMapping("/classe/{id}")
    public Class getClass (@PathVariable("id") final Long id) {
        Optional<Class> classe = classService.getClass(id);
        return classe.orElse(null);
    }
    
    @PutMapping("/update-classe/{id}")
    public Class updateClass(@PathVariable("id") final Long id, @RequestBody Class classEntity) {
        Optional<Class> c = classService.getClass(id);
        if (c.isPresent()) {
            Class currentClasse = c.get();
            String name = classEntity.getName();
            if (name != null) {
                currentClasse.setName(name);
            }
            currentClasse.setNbrPeople(classEntity.getNbrPeople());
            classService.saveClass(currentClasse);
            return currentClasse;
        } else {
            return null;
        }
    }
    
    @DeleteMapping("/delete-classe/{id}")
    public void deleteClasse(@PathVariable("id") final Long id) {
        classService.deleteClass(id);
    }
}
