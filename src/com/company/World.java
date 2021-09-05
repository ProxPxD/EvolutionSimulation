package com.company;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Creature> population;
    private Scenario scenario;

    public World(Scenario scenario){
        this.scenario = scenario;
        initPopulation();
    }

    private void initPopulation() throws NotPositiveException {
        population = new ArrayList<>();
        if (scenario.getStartingPopulation() <= 0)
            throw new NotPositiveException("Population");
        for(int i = 0; i < scenario.getStartingPopulation(); i++){
            population.add(createCreature());
        }
    }

    private Creature createCreature(){
        Creature creature = new Creature(scenario.getNextGenotype());
        return creature;
    }

    public void performDay(){
        System.out.println(population.size());
    }
}
