package com.company.Outside;

import com.company.Creature;
import com.company.Inside.Trait;
import com.company.PayoutMatrix;
import com.company.Population;
import com.company.TraitPredicate;

import static com.company.SimulationConstants.*;

public class Events {

    public static Event createBasicDeathRate(){
        return createBasicDeathRate(1);
    }

    public static Event createBasicDeathRate(int period){
        return createBasicDeathRate(period, DEATH_RATE_NAME);
    }

    public static Event createBasicDeathRate(int period, String traitName){
        TraitPredicate cond = TraitPredicate.makeGreaterThan(new Trait(traitName, randomSupplier));
        Event newEvent = new Event(period, p -> p.stream().filter(c -> !cond.isSatisfiedBy(c)).collect(Population.getCollector()));
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
        TraitPredicate cond = TraitPredicate.makeGreaterThan(new Trait(traitName, randomSupplier));
        Event newEvent = new Event(period,p -> p.add(p.stream().filter(cond::isSatisfiedBy).collect(Population.getCollector())) );
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
