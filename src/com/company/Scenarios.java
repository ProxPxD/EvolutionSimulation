package com.company;

import com.company.Factories.GeneSpace;
import com.company.Inside.Effects;

public class Scenarios {

    public static Scenario simpleReproduction(double deathRate, double reproductionRate){
        Scenario scenario = new Scenario();

        GeneSpace deathRateGene = new GeneSpace(SimulationConstants.DEATH_RATE_NAME);
        deathRateGene.createAllele("A").addEffect(Effects.makeCreationalEffect(SimulationConstants.DEATH_RATE_NAME, deathRate));

        GeneSpace reproductionRateGene = new GeneSpace(SimulationConstants.DUPLICATION_RATE_NAME);
        reproductionRateGene.createAllele("A").addEffect(Effects.makeCreationalEffect(SimulationConstants.DUPLICATION_RATE_NAME, reproductionRate));

        scenario.addGeneSpaces(deathRateGene, reproductionRateGene);
        scenario.setInitialPopulation(SimulationConstants.STARTING_POPULATION);
        scenario.addEvent(Event.createBasicDuplicationRate());
        scenario.addEvent(Event.createBasicDeathRate());

        return scenario;
    }
}
