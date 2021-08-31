package com.company;

import lombok.Getter;

import java.util.function.Function;

public class Effect {

    @Getter
    private String name;
    @Getter
    private String target;
    private TraitCondition traitCondition;
    private Function<Trait, Trait> effect;

    Effect(String target, TraitCondition traitCondition, Function<Trait, Trait> effect){
        this.target = target;
        this.traitCondition = traitCondition;
        this.effect = effect;
    }

    public String getTraitName(){
        return target;
    }

    public boolean shouldInfluence(Creature creature){
        return traitCondition.isSatisfiedBy(creature);
    }

    public Trait apply(Trait trait){
        return effect.apply(trait);
    }
}
