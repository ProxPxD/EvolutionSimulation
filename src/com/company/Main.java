package com.company;

public class Main {

    public static void main(String[] args) {

        Scenario scenario = Scenarios.simpleReproduction(0.02, 0.02);

        Simulation simulation = new Simulation(scenario);
        simulation.setVerbose(true);
        simulation.setSavingState(false);
        simulation.setDayLimit(100);
        simulation.setPopulationLimit(5000);
        simulation.init();

        simulation.simulate();
    }
}
