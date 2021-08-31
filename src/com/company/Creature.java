package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Creature {

    private List<Gene> genome;
    private Phenotype phenotype;


    public Creature(){

    }

    public Phenotype getTraits(){
        return phenotype;
    }

    public Trait getTrait(Trait trait){
        return getTrait(trait.getName());
    }

    public Trait getTrait(String name){
        return phenotype.getTrait(name);
    }

}
