package com.company;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Allele {

    private List<Effect> effects = new ArrayList<>();
    @Getter
    private String geneName;
    @Getter
    private String alleleName;

    public Allele(String geneName, String alleleName){
        this.geneName = geneName;
        this.alleleName = alleleName;
    }

    public String getFullName(){
        return geneName + "." + alleleName;
    }

    public Allele addEffect(Effect effect){
        effects.add(effect);
        return this;
    }

    public void removeEffect(Effect effect){
        effects.remove(effect);
    }

    public void removeEffect(String effectName){
        findEffect(effectName).ifPresent(this::removeEffect);
    }

    public Effect getEffect(String name){
        return findEffect(name).orElse(null);
    }

    private Optional<Effect> findEffect(String effectName){
        return effects.stream().filter(e -> e.getName().equals(effectName)).findFirst();
    }


    public boolean shouldInfluence(Creature creature){
        return effects.stream().anyMatch(e -> e.shouldInfluence(creature));
    }

    public void influence(Creature creature){
        effects.stream().filter(e -> e.shouldInfluence(creature)).forEach(creature::applyEffect);
    }
}
