package com.company.Outside;

import com.company.Creature;
import com.company.PayoutMatrix;
import com.company.Population;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.company.SimulationConstants.*;

public class Events {

    public static Event createBasicDeathRate(double rate){
        return createBasicDeathRate(1, rate);
    }

    public static Event createBasicDeathRate(int period, double rate) {
        Event newEvent = new Event(period, p -> p.stream().filter(c -> rate <= randDouble.get()).collect(Population.getCollector()));
        newEvent.setName(DEATH_RATE_NAME);
        return newEvent;
    }


    public static Event createBasicDuplicationRate(double rate){
        return createBasicDuplicationRate(1, rate);
    }

    public static Event createBasicDuplicationRate(int period, double rate) {
        Event newEvent = new Event(period, p -> p.stream().map(c -> copyCreature((int) (rate + randDouble.get()), c)).reduce(p, Population::add));
        newEvent.setName(DUPLICATION_RATE_NAME);
        return newEvent;
    }


//    public static Event createDuelEvent(int period, TraitPredicate condition, PayoutMatrix matrix){
//        Event newEvent = new Event(period, condition, ());
//        newEvent.setName(DUEL_NAME);
//        return newEvent;
//    }


//    public static Event createConditionalDeathEvent(int period, TraitPredicate condition) {
//        Event newEvent = new Event(period, condition, Population::removeAll);
//        newEvent.setName("Conditional Death");
//        return newEvent;
//    }

    public static Population copyCreature(int n, Creature creature){
        return Stream.generate(() -> creature).limit(n).collect(Population.getCollector());
    }


    public static Population performDuel(Population population, PayoutMatrix matrix, String targetTraitName){
        Population result = new Population();
        while (population.size() % 2 != 0)
            result.add(population.pop());

        while (!population.isEmpty()){
            Creature creature1 = population.pop();
            Creature creature2 = population.pop();
            var scores = matrix.get(creature1, creature2);
            creature1.getTrait(targetTraitName).add(scores.getKey());
            creature2.getTrait(targetTraitName).add(scores.getValue());
            result.addAll(creature1, creature2);
        }
        return result;
    }
}
