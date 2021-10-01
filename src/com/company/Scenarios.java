package com.company;

import com.company.Outside.Events;

public class Scenarios {

    public static Scenario makeSimpleDuplicationDeathScenario(double duplicationRate, double deathRate){
        Scenario scenario = new Scenario();

        scenario.setInitialPopulation(SimulationConstants.STARTING_POPULATION);
        scenario.addEvent(Events.createBasicDuplicationRate(duplicationRate));
        scenario.addEvent(Events.createBasicDeathRate(deathRate));

        return scenario;
    }
}
