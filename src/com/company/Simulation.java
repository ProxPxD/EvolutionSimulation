package com.company;

import lombok.Setter;

import static com.company.SimulationConstants.DAY_LIMIT;
import static com.company.SimulationConstants.POPULATION_LIMIT;

public class Simulation {

    @Setter private int dayLimit;
    private int day;
    @Setter int populationLimit;
    Scenario scenario;

    private World world;

    public Simulation(){
        day = 0;
        dayLimit = DAY_LIMIT;
        populationLimit = POPULATION_LIMIT;
    }

    public Simulation(Scenario scenario){
        this();
        this.scenario = scenario;
    }

    public void init(){
        initStartingValues();
        world = new World(scenario.getStartingPopulation());
    }

    private void initStartingValues() throws NotPositiveException {
        if (dayLimit <= 0)
            throw new NotPositiveException("Day limit");
    }

    public void simulate(){
        while (day < dayLimit){
            System.out.print(day);
            System.out.print(": ");
            world.performDay();
            day++;
        }
    }
}
