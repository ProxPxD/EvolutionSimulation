package com.company.Inside;

import com.company.TraitPredicate;

public class Effects {

    public static Effect makeCreationalEffect(Trait toCreate){
        return new Effect(toCreate.getName(), TraitPredicate.makeNotExist(toCreate), t -> toCreate);
    }

    public static Effect makeCreationalEffect(String name, double value){
        return new Effect(name, TraitPredicate.makeNotExist(name), t -> new Trait(name, value));
    }

}
