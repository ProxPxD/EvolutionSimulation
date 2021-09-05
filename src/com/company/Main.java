package com.company;

public class Main {

    public static void main(String[] args) {
        Scenario scenario = new Scenario();

        GeneSpace deathRate = new GeneSpace("deathRate");
        deathRate.createAllele("A").addEffect(Effect.makeCreationalEffect(new Trait("deathRate", 0.02)));
        GeneSpace reproductionRate = new GeneSpace("reproductionRate");
        deathRate.createAllele("A").addEffect(Effect.makeCreationalEffect(new Trait("reproductionRate", 0.05)));

        scenario.addGeneSpaces(deathRate, reproductionRate);

        Simulation simulation = new Simulation(scenario);
        simulation.setDayLimit(200);
        simulation.init();
        simulation.simulate();
    }
}
