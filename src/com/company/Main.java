package com.company;

public class Main {

    public static void main(String[] args) {
        Scenario scenario = new Scenario();

        GeneSpace deathRate = new GeneSpace("deathRate");
        deathRate.createAllele("A").addEffect(Effect.makeCreationalEffect("deathRate", 0.02));
        GeneSpace reproductionRate = new GeneSpace("reproductionRate");
        reproductionRate.createAllele("A").addEffect(Effect.makeCreationalEffect("reproductionRate", 0.05));

        scenario.addGeneSpaces(deathRate, reproductionRate);
        scenario.setStartingPopulation(10);

        Simulation simulation = new Simulation(scenario);
        simulation.setDayLimit(200);
        simulation.init();
        simulation.simulate();
    }
}
