package com.company;

import java.util.ArrayList;
import java.util.List;

public class Population extends ArrayList<Creature>{

    public Population(Scenario scenario){
        if (scenario.getInitialPopulation() <= 0)
            throw new NotPositiveException("Population");

        for(int i = 0; i < scenario.getInitialPopulation(); i++){
            add(createCreature(scenario));
        }
    }

    public Population(List<Creature> population){
        addAll(population);
    }

    private Creature createCreature(Scenario scenario){
        Creature creature = new Creature(scenario.getNextGenotype());
        return creature;
    }

    public Population addMembers(Population population){
        Population newPopulation = new Population(this);
        newPopulation.addAll(population);
        return newPopulation;
    }

}
