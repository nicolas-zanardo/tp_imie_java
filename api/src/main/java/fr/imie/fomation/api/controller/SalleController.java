package fr.imie.fomation.api.controller;

import fr.imie.fomation.api.model.Salle;
import fr.imie.fomation.api.service.SalleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class SalleController {

    @Autowired
    private SalleService SalleService;

    /**
     * @author jason
     * @return SalleService
     */
    @GetMapping("/salles")
    public Iterable<Salle> getSalles() {
        return SalleService.getSalles();
    }

    /**
     * @author jason
     * @param salle Salle
     * @return SalleService
     */
    @PostMapping("/add-salle")
    public Salle createSalle(@RequestBody Salle salle) {
        return SalleService.saveSalle(salle);
    }

    /**
     * @author jason
     * @param id Long
     * @return Salle
     */
    @GetMapping("/salle/{id}")
    public Salle getSalle (@PathVariable("id") final Long id) {
        Optional<Salle> salle = SalleService.getSalle(id);
        return salle.orElse(null);
    }

    /**
     * UPDATE - SALLE
     * @author jason
     * @author Nicolas Zanardo - Correction des setter
     * @param id Long
     * @param salle Salle
     * @return Salle
     */
    @PutMapping("/update-salle/{id}")
    public Salle updateSalle(@PathVariable("id") final Long id, @RequestBody Salle salle) {
        Optional<Salle> s = SalleService.getSalle(id);
        if(s.isPresent()) {
            Salle currentSalle = s.get();
            if(salle.getNom() != null) {
                currentSalle.setNom(salle.getNom());
            }
            if(salle.getTypeSalle() != null) {
                currentSalle.setTypeSalle(salle.getTypeSalle());
            }
            currentSalle.setNombrePlaces(salle.getNombrePlaces());
            SalleService.saveSalle(currentSalle);
            return currentSalle;
        } else {
            return null;
        }
    }

    /**
     * @author json
     * @param id Long
     */
    @DeleteMapping("/delete-salle/{id}")
    public void deleteSalle(@PathVariable("id") final Long id) {
        SalleService.deleteSalle(id);
    }
}
