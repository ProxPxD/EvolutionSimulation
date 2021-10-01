package com.company;

import com.company.Inside.Effect;
import com.company.Inside.Genotype;
import com.company.Inside.Phenotype;
import com.company.Inside.Trait;

public class Creature {

    private Genotype genotype;
    private Phenotype phenotype;

    public Creature(Genotype genotype){
        this.genotype = genotype;
        this.genotype.setOwner(this);
        this.phenotype = new Phenotype();
    }

    public Phenotype getTraits(){
        return phenotype;
    }

    public Trait getTrait(Trait trait){
        return getTrait(trait.getType());
    }

    public Trait getTrait(String name){
        return phenotype.getTrait(name);
    }

    public void applyEffect(Effect effect){
        phenotype.applyEffect(effect);
    }

    public void performGenes(){
        if (genotype.shouldInfluence())
            genotype.influence();
    }
}
