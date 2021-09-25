package com.company;

import lombok.Setter;

import java.util.HashMap;

import static com.company.SimulationConstants.DAY_LIMIT;
import static com.company.SimulationConstants.POPULATION_LIMIT;

public class Simulation {

    @Setter private int dayLimit;
    private int day;
    @Setter int populationLimit;
    @Setter boolean verbose = false;
    @Setter boolean savingState = true;

    private World world;
    private Scenario scenario;
    private HashMap<Integer, Population> data;


    public Simulation(Scenario scenario){
        day = 0;
        dayLimit = DAY_LIMIT;
        populationLimit = POPULATION_LIMIT;
        this.scenario = scenario;
        world = new World();
    }

    public void init(){
        world.init(scenario);
    }

    private void initStartingValues() throws NotPositiveException {
        if (dayLimit <= 0)
            throw new NotPositiveException("Day limit");
    }

    public void simulate(){
        saveStateIfSaving();
        printStateIfVerbose();
        while (areSimulationConditionsSatisfied()){
            world.performDay();
            ++day;
            printStateIfVerbose();
            saveStateIfSaving();
        }
    }

    private boolean areSimulationConditionsSatisfied(){
        return day < dayLimit && world.getPopulation().size() < populationLimit;
    }

    private void printStateIfVerbose(){
        if (verbose)
            printState();
    }

    private void printState(){
        int populationSize = world.getPopulation().size();
        String info = day + ": " + populationSize + "";
        System.out.println(info);
    }

    private void saveStateIfSaving(){
        if (savingState)
            saveState();
    }

    private void saveState(){
        data.put(day, world.getPopulation());
    }
}
