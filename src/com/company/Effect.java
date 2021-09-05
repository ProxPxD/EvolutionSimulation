package com.company;

import lombok.Getter;

import java.util.function.Function;

public class Effect {

    public static Effect makeCreationalEffect(Trait toCreate){
        return new Effect(toCreate.getName(), TraitCondition.makeNotExist(toCreate), t -> toCreate);
    }

    public static Effect makeCreationalEffect(String name, double value){
        return new Effect(name, TraitCondition.makeNotExist(name), t -> new Trait(name, value));
    }

    @Getter
    private String name;
    @Getter
    private String target;
    private TraitCondition traitCondition;
    private Function<Trait, Trait> effect;

    Effect(String targetTraitName, TraitCondition traitCondition, Function<Trait, Trait> effect){
        this.target = targetTraitName;
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
