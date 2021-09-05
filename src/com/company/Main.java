package com.company;

import java.util.Random;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scenario scenario = new Scenario();

        GeneSpace deathRate = new GeneSpace(SimulationConstants.DEATH_RATE_NAME);
        deathRate.createAllele("A").addEffect(Effect.makeCreationalEffect(SimulationConstants.DEATH_RATE_NAME, 0.02));
        GeneSpace reproductionRate = new GeneSpace(SimulationConstants.DUPLICATION_RATE_NAME);
        reproductionRate.createAllele("A").addEffect(Effect.makeCreationalEffect(SimulationConstants.DUPLICATION_RATE_NAME, 0.02));
        scenario.addGeneSpaces(deathRate, reproductionRate);
        scenario.setInitialPopulation(100);

        scenario.addEvent(Event.createBasicDuplicationRate());
        scenario.addEvent(Event.createBasicDeathRate());

        Simulation simulation = new Simulation(scenario);
        simulation.setDayLimit(100);
        simulation.setPopulationLimit(300000);
        simulation.init();
        simulation.simulate();
    }
}
