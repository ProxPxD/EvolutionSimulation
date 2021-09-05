package com.company;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Gene {

    private List<Effect> effects = new ArrayList<>();
    @Getter @Setter
    private String geneName;
    @Getter
    private String alleleName;

    public Gene(String alleleName){
        this.alleleName = alleleName;
    }

    public void addEffect(Effect effect){
        effects.add(effect);
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
