package org.example.pojo;

import org.example.stateType.Type;

import java.util.List;

public class Salameche extends Pokemon{

    private Type type;


    public Salameche(String prenom, List<Capacite> capacites, Integer niveau, Boolean isShiny, String nature, Type type) {
        super(prenom, capacites, niveau, isShiny, nature);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
