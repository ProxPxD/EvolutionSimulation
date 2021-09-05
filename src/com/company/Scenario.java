package com.company;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scenario {

    @Setter @Getter
    private int initialPopulation;
    @Getter
    private List<GeneSpace> geneSpaces;
    @Getter
    private List<Event> events;

    public Scenario(){
        initialPopulation = SimulationConstants.STARTING_POPULATION;
        geneSpaces = new ArrayList<>();
        events = new ArrayList<>();
    }

    public void init(){
        geneSpaces.forEach(s -> s.computeNumberOfIndividualsPerAllele(initialPopulation));
    }

    public void addGeneSpaces(GeneSpace... geneSpaces){
        Arrays.stream(geneSpaces).forEach(this::addGeneSpace);
    }

    public void addGeneSpace(GeneSpace geneSpaceVariant){
        if (this.geneSpaces.stream().map(GeneSpace::getGeneName).anyMatch(n -> n.equals(geneSpaceVariant.getGeneName())))
            throw new IllegalArgumentException("Allele group of the name \"" + geneSpaceVariant.getGeneName() + "\" already exists");
        this.geneSpaces.add(geneSpaceVariant);
    }

    public void addEvent(Event event){
        events.add(event);
    }

    public Genotype getNextGenotype(){
        return new Genotype(geneSpaces.stream().map(GeneSpace::getNextAllele).collect(Collectors.toList()));
    }
}
