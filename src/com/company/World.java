package com.company;

public class World {
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
        for(Event event: scenario.getEvents()){
            Population toApply = event.filter(population);
            population = event.apply(toApply);
        }
        System.out.println(population.size());
    }
}
