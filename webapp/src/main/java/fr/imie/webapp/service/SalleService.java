package fr.imie.webapp.service;

import fr.imie.webapp.model.Salle;
import fr.imie.webapp.model.SalleFormData;
import fr.imie.webapp.model.TypeSalle;
import fr.imie.webapp.repository.SalleProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class SalleService {

    @Autowired
    private SalleProxy salleProxy;

    public Salle getSalle(final int id)  {
        return  salleProxy.getSalle(id);
    }

    public void saveSalle(Salle salle) {
        Salle saveSalle = null;
        if(salle.getId() == null) {
            saveSalle = salleProxy.createSalle(salle);
        } else {
            // TODO create update
        }
    }

    public void deleteSalle(final int id) { salleProxy.deleteSalle(id); }

    public Iterable<Salle> getSalles() {
        return salleProxy.getSalles();
    }
}
