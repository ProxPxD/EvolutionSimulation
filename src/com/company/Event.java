package com.company;

import lombok.Getter;

import java.util.function.Function;
import java.util.stream.Collectors;

public class Event {

    @Getter
    private Integer period;
    private TraitCondition traitCondition;
    private Function<Population, Population> effect;

    public static Event createDailyEvent(TraitCondition traitCondition, Function<Population, Population> effect){
        return new Event(1, traitCondition, effect);
    }

    public Event(int period, TraitCondition traitCondition, Function<Population, Population> effect){
        this.period = period;
        this.traitCondition = traitCondition;
        this.effect = effect;
    }

    public Population filter(Population population){
        return new Population(population.stream().filter(traitCondition::isSatisfiedBy).collect(Collectors.toList()));
    }

    public Population apply(Population population){
        return effect.apply(population);
    }
}
