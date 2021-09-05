package com.company;

import java.util.ArrayList;
import java.util.List;

public class World {
    private List<Creature> population;

    public World(int startingPopulation){
        initPopulation(startingPopulation);
    }

    private void initPopulation(int startingPopulation) throws NotPositiveException {
        population = new ArrayList<>();
        if (startingPopulation <= 0)
            throw new NotPositiveException("Population");
        for(int i = 0; i < startingPopulation; i++){
            population.add(createCreature());
        }
    }

    private Creature createCreature(){
        Creature creature = null;
        return creature;
    }

    public void performDay(){
        System.out.println(population.size());
    }
}
