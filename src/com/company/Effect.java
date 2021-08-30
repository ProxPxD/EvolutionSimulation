package com.company;

import java.util.function.Function;

public class Effect {

    private Trait trait;
    private TraitCondition traitConditions;
    private Function<Trait, Trait> effect;
}
