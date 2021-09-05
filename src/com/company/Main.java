package com.company;

import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scenario scenario = new Scenario();

        GeneSpace deathRate = new GeneSpace("deathRate");
        deathRate.createAllele("A").addEffect(Effect.makeCreationalEffect("deathRate", 0.486));
        GeneSpace reproductionRate = new GeneSpace("reproductionRate");
        reproductionRate.createAllele("A").addEffect(Effect.makeCreationalEffect("reproductionRate", 0.05));
        scenario.addGeneSpaces(deathRate, reproductionRate);
        scenario.setInitialPopulation(100);

        scenario.addEvent(Event.createDailyEvent(TraitCondition.makeTrue("deathRate"), p -> new Population(p.stream().filter(c -> c.getTrait("deathRate").getValue() < new Random().nextDouble()).collect(Collectors.toList()))));
        scenario.addEvent(Event.createDailyEvent(TraitCondition.makeTrue("reproductionRate"), p -> p.addMembers(new Population(p.stream().filter(c -> c.getTrait("reproductionRate").getValue() < new Random().nextDouble()).collect(Collectors.toList())))));

        Simulation simulation = new Simulation(scenario);
        simulation.setDayLimit(1000);
        simulation.init();
        simulation.simulate();
    }
}
