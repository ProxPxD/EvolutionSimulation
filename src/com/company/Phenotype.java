package com.company;

import java.util.HashMap;

public class Phenotype {
    private HashMap<String, Trait> traits = new HashMap<>();

    public void addTrait(String name){
        traits.put(name, new Trait(name));
    }

    public Trait getTrait(String name){
        return traits.getOrDefault(name, null); //TODO think of the null removal
    }
}
