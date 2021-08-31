package com.company;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class Genotype {
    @Getter
    private List<Gene> genes = new ArrayList<>();
    private Creature owner;

    public Genotype(Creature owner){
        this.owner = owner;
    }


    public boolean shouldInfluence(){
        return genes.stream().anyMatch(g -> g.shouldInfluence(owner));
    }

    public void influence(){
        genes.stream().filter(g -> g.shouldInfluence(owner)).forEach(g -> g.influence(owner));
    }

}
