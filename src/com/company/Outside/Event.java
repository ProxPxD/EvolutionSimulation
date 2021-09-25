package com.company.Outside;

import com.company.Population;
import com.company.TraitPredicate;
import lombok.Getter;
import lombok.Setter;

import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class Event {


    @Getter @Setter
    private String name;
    @Getter
    private Integer period;
    private TraitPredicate traitPredicate;
    private BiFunction<Population, Population, Population> effect;

    public Event(int period, TraitPredicate traitPredicate, BiFunction<Population, Population, Population> effect){
        this.period = period;
        this.traitPredicate = traitPredicate;
        this.effect = effect;
    }

    public Population filter(Population population){
        return new Population(population.stream().filter(traitPredicate::isSatisfiedBy).collect(Collectors.toList()));
    }

    public Population apply(Population population, Population filtered){
        return effect.apply(population, filtered);
    }
}
