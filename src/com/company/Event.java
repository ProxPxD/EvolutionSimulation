package com.company;

import lombok.Getter;
import lombok.Setter;

import java.util.function.BiFunction;
import java.util.stream.Collectors;

import static com.company.SimulationConstants.*;

public class Event {


    @Getter @Setter
    private String name;
    @Getter
    private Integer period;
    private TraitCondition traitCondition;
    private BiFunction<Population, Population, Population> effect;

    public static Event createDailyEvent(TraitCondition traitCondition, BiFunction<Population, Population, Population> effect){
        Event newEvent = new Event(1, traitCondition, effect);
        newEvent.setName("Daily Event");
        return newEvent;
    }

    public static Event createBasicDeathRate(){
        return createBasicDeathRate(1);
    }

    public static Event createBasicDeathRate(int period){
        return createBasicDeathRate(period, DEATH_RATE_NAME);
    }

    public static Event createBasicDeathRate(int period, String traitName){
        Event newEvent = new Event(period, TraitCondition.makeGreaterThan(new Trait(traitName, randomSupplier)), (p, f) -> p.removeAll(f));
        newEvent.setName(DEATH_RATE_NAME);
        return newEvent;
    }

    public static Event createBasicDuplicationRate(){
        return createBasicDuplicationRate(1);
    }

    public static Event createBasicDuplicationRate(int period){
        return createBasicDuplicationRate(period, DUPLICATION_RATE_NAME);
    }

    public static Event createBasicDuplicationRate(int period, String traitName){
        Event newEvent = new Event(period, TraitCondition.makeGreaterThan(new Trait(traitName, randomSupplier)), Population::add);
        newEvent.setName(DUPLICATION_RATE_NAME);
        return newEvent;
    }

    public Event(int period, TraitCondition traitCondition, BiFunction<Population, Population, Population> effect){
        this.period = period;
        this.traitCondition = traitCondition;
        this.effect = effect;
    }

    public Population filter(Population population){
        return new Population(population.stream().filter(traitCondition::isSatisfiedBy).collect(Collectors.toList()));
    }

    public Population apply(Population population, Population filtered){
        return effect.apply(population, filtered);
    }
}
