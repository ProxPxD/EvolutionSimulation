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

}
