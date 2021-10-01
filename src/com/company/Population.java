package com.company;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static com.company.SimulationConstants.randIndex;

public class Population extends ArrayList<Creature>{

    public Population(){

    }

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

    public Population add(Population population){
        Population newPopulation = new Population(this);
        newPopulation.addAll(population);
        return newPopulation;
    }

    public void addAll(Creature... creatures){
        Arrays.stream(creatures).forEach(this::add);
    }

    public Population removeAll(Population population){
        Population newPopulation = new Population(this);
        population.forEach(newPopulation::remove);
        return newPopulation;
    }

    public Creature getRandom(){
        return get(randIndex.apply(size()));
    }

    public Creature pop(){
        Creature creature = getRandom();
        remove(creature);
        return creature;
    }

    public static Collector<Creature, Population, Population> getCollector(){
        return Collector.of(Population::new, Population::add, Population::add, Function.identity());
    }
}
