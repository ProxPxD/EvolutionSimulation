package com.company;

import com.company.Outside.Event;
import lombok.Getter;

import java.util.List;
import java.util.Random;

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
        applyEvents();
    }

    private void applyEvents(){
        for(Event event: events)
            population = event.apply(population);
    }
}
