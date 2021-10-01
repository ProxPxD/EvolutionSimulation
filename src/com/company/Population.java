package com.company;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

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

    public Creature pop(){
        Random random = new Random();
        int index = random.nextInt(size());
        Creature toReturn = get(index);
        remove(index);
        return toReturn;
    }

    public static Collector<Creature, Population, Population> getCollector(){
        return Collector.of(Population::new, Population::add, Population::add, Function.identity());
    }
}
