package com.company;

public class Creature {

    private Genotype genotype;
    private Phenotype phenotype;

    public Creature(Genotype genotype){
        this.genotype = genotype;
    }

    public Phenotype getTraits(){
        return phenotype;
    }

    public Genotype getGenotype(){
        return genotype;
    }

    public Trait getTrait(Trait trait){
        return getTrait(trait.getName());
    }

    public Trait getTrait(String name){
        return phenotype.getTrait(name);
    }

    public void applyEffect(Effect effect){
        phenotype.applyEffect(effect);
    }
}
