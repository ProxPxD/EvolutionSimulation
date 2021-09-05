package com.company;

import lombok.Getter;

public class World {
    @Getter
    private Population population;
    private Scenario scenario;

    public World(Scenario scenario){
        this.scenario = scenario;
    }

    public void init(){
        scenario.init();
        population = new Population(scenario);
    }

    public void performDay(){
        population.forEach(Creature::performGenes);
        System.out.print(population.size() + ": ");
        for(Event event: scenario.getEvents()){
            Population toApply = event.filter(population);
            population = event.apply(population, toApply);
//            System.out.print(event.getName() + " - " + population.size() + ", ");
        }
        System.out.println();
    }
}
