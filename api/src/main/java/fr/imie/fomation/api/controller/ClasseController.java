package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Classe;
import fr.imie.fomation.api.service.ClasseService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author jason
 */
@RestController
@RequestMapping(path = "/api")
public class ClasseController {
    
    @Autowired
    private ClasseService ClasseService;
    
    @GetMapping("/classes")
    public Iterable<Classe> getClasses() {
        return ClasseService.getClasses();
    }
    
    @PostMapping("/add-classe")
    public Classe createClasse(@RequestBody Classe classe) {
        return ClasseService.saveClasse(classe);
    }
    
    @GetMapping("/classe/{id}")
    public Classe getClasse (@PathVariable("id") final Long id) {
        Optional<Classe> classe = ClasseService.getClasse(id);
        return classe.orElse(null);
    }
    
    @PutMapping("/update-classe/{id}")
    public Classe updateClasse(@PathVariable("id") final Long id, @RequestBody Classe classe) {
        Optional<Classe> c = ClasseService.getClasse(id);
        if (c.isPresent()) {
            Classe currentClasse = c.get();
            String nom = classe.getNom();
            if (nom != null) {
                currentClasse.setNom(nom);
            }
            currentClasse.setNombrePersonnes(classe.getNombrePersonnes());
            ClasseService.saveClasse(currentClasse);
            return currentClasse;
        } else {
            return null;
        }
    }
    
    @DeleteMapping("/delete-classe/{id}")
    public void deleteClasse(@PathVariable("id") final Long id) {
        ClasseService.deleteClasse(id);
    }
}
