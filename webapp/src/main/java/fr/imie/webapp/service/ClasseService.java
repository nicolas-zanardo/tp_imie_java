package fr.imie.webapp.service;

import fr.imie.webapp.model.Classe;
import fr.imie.webapp.repository.ClasseProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class ClasseService {

    @Autowired
    private ClasseProxy classeProxy;

    public void saveClasse(Classe classe) {
        Classe savedClasse = null;
        if(classe.getId() == null) {
            savedClasse = classeProxy.createClasse(classe);
        } else {
            savedClasse = classeProxy.updateClasse(classe);
        }
    }

    public Iterable<Classe> getClasses() {
        return classeProxy.getClasses();
    }

    public Classe getClasse(final int id) {
        return classeProxy.getClasse(id);
    }

    public void deleteClasse(final int id) {
        classeProxy.deleteClasse(id);
    }

}
