package com.company;

public class Main {

    public static void main(String[] args) {

        Scenario scenario = Scenarios.simpleReproduction(0.02, 0.02);

        Simulation simulation = new Simulation(scenario);
        simulation.setDayLimit(100);
        simulation.setPopulationLimit(1000);
        simulation.init();

        simulation.simulate();
    }
}
