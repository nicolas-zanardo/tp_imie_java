package fr.imie.fomation.api._init_data;

import fr.imie.fomation.api.model.TypeSalle;

public class DataFakerTypeSalle {

    private static DataFakerTypeSalle instance = null;
    private static TypeSalle[] typeSalles;

    public static DataFakerTypeSalle getInstance() {
        createTypeSalle();
        if(instance == null) {
            instance = new DataFakerTypeSalle();
        }
        return instance;

    }

    private static TypeSalle createTypeSalle() {
        TypeSalle typeSalle = new TypeSalle();
        typeSalle.setNom("Informatique TEST");
        return typeSalle;
    }

}
