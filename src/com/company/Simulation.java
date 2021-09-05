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
        world = new World(scenario);
    }

    public void init(){
        world.init();
    }

    private void initStartingValues() throws NotPositiveException {
        if (dayLimit <= 0)
            throw new NotPositiveException("Day limit");
    }

    public void simulate(){
        while (areSimulationConditionsSatisfied()){
            System.out.print(day + ": ");
            world.performDay();
            day++;
        }
    }

    private boolean areSimulationConditionsSatisfied(){
        return day < dayLimit && world.getPopulation().size() < populationLimit;
    }
}
