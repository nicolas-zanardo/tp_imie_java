package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Salle;
import fr.imie.fomation.api.service.SalleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 * @author jason
 */
@RestController
@RequestMapping(path = "/api")
public class SalleController {
    
    @Autowired
    private SalleService SalleService;
    
    @GetMapping("/salles")
    public Iterable<Salle> getSalles() {
        return SalleService.getSalles();
    }
    
    @PostMapping("/add-salle")
    public Salle createSalle(@RequestBody Salle salle) {
        return SalleService.saveSalle(salle);
    }
    
    @GetMapping("/salle/{id}")
    public Salle getSalle (@PathVariable("id") final Long id) {
        Optional<Salle> salle = SalleService.getSalle(id);
        return salle.orElse(null);
    }
    
    @PutMapping("/salle/{id}")
    public Salle updateSalle(@PathVariable("id") final Long id, @RequestBody Salle salle) {
        Optional<Salle> s = SalleService.getSalle(id);
        if(s.isPresent()) {
            Salle currentSalle = s.get();
            String nom = salle.getNom();
            if (nom != null) {
                currentSalle.setNom(nom);
            }
            SalleService.saveSalle(currentSalle);
            return currentSalle;
        } else {
            return null;
        }
    }
    
    @DeleteMapping("/salle/{id}")
    public void deleteSalle(@PathVariable("id") final Long id) {
        SalleService.deleteSalle(id);
    }
}
