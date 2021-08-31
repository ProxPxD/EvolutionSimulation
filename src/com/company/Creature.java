package com.company;

public class Creature {

    private Genotype genome;
    private Phenotype phenotype;

    public Creature(Genotype genotype){

    }

    public Phenotype getTraits(){
        return phenotype;
    }

    public Genotype getGenome(){
        return genome;
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
