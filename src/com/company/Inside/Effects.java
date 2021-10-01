package com.company.Inside;

import com.company.TraitPredicate;

import java.util.function.Function;

public class Effects {

    public static Effect makeCreationalEffect(Trait toCreate){
        return new Effect(toCreate.getType(), TraitPredicate.makeNotExist(toCreate), t -> toCreate);
    }

    public static Effect makeCreationalEffect(String name, double value){
        return new Effect(name, TraitPredicate.makeNotExist(name), t -> new Trait(name, value));
    }

    public static Effect makeAddScoreEffect(String name, double score){
        return makeConditionalAddScoreEffect(name, TraitPredicate.makeTrue(name), score);
    }

    public static Effect makeConditionalAddScoreEffect(String name, TraitPredicate condition, double score){
        return makeConditionalModificatingEffect(name, condition, t -> t.add(score));
    }

    public static Effect makeConditionalModificatingEffect(String name, TraitPredicate condition, Function<Trait, Trait> effect){
        return new Effect(name, condition, effect);
    }

}
