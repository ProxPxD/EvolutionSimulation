package com.company;

import lombok.Getter;

import java.util.List;

public class World {
    @Getter
    private Population population;
    private List<Event> events;

    public World(){

    }

    public void init(Scenario scenario){
        scenario.init();
        population = new Population(scenario);
        events = scenario.getEvents();
    }

    public void performDay(){
        population.forEach(Creature::performGenes);
        for(Event event: events){
            Population toApply = event.filter(population);
            population = event.apply(population, toApply);
        }
        System.out.println();
    }
}
