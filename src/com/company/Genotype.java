package com.company;

import lombok.Getter;

import java.util.List;

public class Genotype {
    @Getter
    private List<Allele> genes;
    private Creature owner;

    public Genotype(List<Allele> genes){
        this.genes = genes;
    }

    public void setOwner(Creature owner){
        this.owner = owner;
    }

    public boolean shouldInfluence(){
        return genes.stream().anyMatch(g -> g.shouldInfluence(owner));
    }

    public void influence(){
        genes.stream().filter(g -> g.shouldInfluence(owner)).forEach(g -> g.influence(owner));
    }

}
