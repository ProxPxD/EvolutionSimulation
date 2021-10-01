package com.company.Inside;

import com.company.Creature;
import com.company.TraitPredicate;
import lombok.Getter;

import java.util.function.Function;

public class Effect {

    @Getter
    private String name;
    @Getter
    private String target;
    private TraitPredicate traitPredicate;
    private Function<Trait, Trait> effect;

    Effect(String targetTraitName, Function<Trait, Trait> effect){
        this(targetTraitName, TraitPredicate.makeTrue(targetTraitName), effect);
    }

    Effect(String targetTraitName, TraitPredicate traitPredicate, Function<Trait, Trait> effect){
        this.target = targetTraitName;
        this.traitPredicate = traitPredicate;
        this.effect = effect;
    }

    public String getTraitName(){
        return target;
    }

    public boolean shouldInfluence(Creature creature){
        return traitPredicate.isSatisfiedBy(creature);
    }

    public Trait apply(Trait trait){
        return effect.apply(trait);
    }
}
