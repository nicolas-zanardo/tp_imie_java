package fr.imie.webapp.service;

import fr.imie.webapp.model.Class;
import fr.imie.webapp.repository.ClassProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Nicolas Zanardo
 */
@Data
@Service
public class ClassService {

    @Autowired
    private ClassProxy classeProxy;

    public void saveClass(Class classe) {
        Class savedClasse = null;
        if(classe.getId() == null) {
            savedClasse = classeProxy.createClass(classe);
        } else {
            savedClasse = classeProxy.updateClass(classe);
        }
    }

    public Iterable<Class> getClasses() {
        return classeProxy.getClasses();
    }

    public Class getClass(final int id) {
        return classeProxy.getClass(id);
    }

    public void deleteClass(final int id) {
        classeProxy.deleteClass(id);
    }

}
