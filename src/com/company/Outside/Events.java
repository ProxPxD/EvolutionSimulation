package com.company.Outside;

import com.company.Inside.Trait;
import com.company.Population;
import com.company.TraitPredicate;

import java.util.function.BiFunction;

import static com.company.SimulationConstants.*;

public class Events {

    public static Event createDailyEvent(TraitPredicate traitPredicate, BiFunction<Population, Population, Population> effect){
        Event newEvent = new Event(1, traitPredicate, effect);
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
        Event newEvent = new Event(period, TraitPredicate.makeGreaterThan(new Trait(traitName, randomSupplier)), (p, f) -> p.removeAll(f));
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
        Event newEvent = new Event(period, TraitPredicate.makeGreaterThan(new Trait(traitName, randomSupplier)), Population::add);
        newEvent.setName(DUPLICATION_RATE_NAME);
        return newEvent;
    }
}
