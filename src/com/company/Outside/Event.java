package com.company.Outside;

import com.company.Population;
import com.company.TraitPredicate;
import lombok.Getter;
import lombok.Setter;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Event {


    @Getter @Setter
    private String name;
    private Integer period;
    private TraitPredicate traitPredicate;
    private Function<Population, Population>  effect;

    public Event(Function<Population, Population> effect){
        this(1, effect);

    }
    public Event(int period, Function<Population, Population> effect){
        this.effect = effect;
        this.period = period;
    }

    public Event setPeriod(int period){
        this.period = period;
        return this;
    }

    public Event setTraitPredicate(TraitPredicate traitPredicate) {
        this.traitPredicate = traitPredicate;
        return this;
    }

    public Event setEffect(Function<Population, Population> effect) {
        this.effect = effect;
        return this;
    }

    public Population filter(Population population){
        return new Population(population.stream().filter(traitPredicate::isSatisfiedBy).collect(Collectors.toList()));
    }

    public Population apply(Population population){
        return effect.apply(population);
    }
}
