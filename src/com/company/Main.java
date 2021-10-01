package com.company;

public class Main {

    public static void main(String[] args) {

        Scenario scenario = Scenarios.makeSimpleDuplicationDeathScenario(0.05, 0.05);

        Simulation simulation = new Simulation(scenario);
        simulation.setVerbose(true);
        simulation.setSavingState(false);
        simulation.setDayLimit(500);
        simulation.setPopulationLimit(5000);
        simulation.init();

        simulation.simulate();
    }
}
