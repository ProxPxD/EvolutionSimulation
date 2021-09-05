package com.company;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Scenario {

    @Setter @Getter
    private int startingPopulation;
    @Getter
    private List<GeneSpace> geneSpaces;

    public Scenario(){
        startingPopulation = SimulationConstants.STARTING_POPULATION;
        geneSpaces = new ArrayList<>();
    }

    public void init(){
        geneSpaces.forEach(s -> s.computeNumberOfIndividualsPerAllele(startingPopulation));
    }

    public void addGeneSpaces(GeneSpace... geneSpaces){
        Arrays.stream(geneSpaces).forEach(this::addGeneSpace);
    }

    public void addGeneSpace(GeneSpace geneSpaceVariant){
        if (this.geneSpaces.stream().map(GeneSpace::getGeneName).anyMatch(n -> n.equals(geneSpaceVariant.getGeneName())))
            throw new IllegalArgumentException("Allele group of the name \"" + geneSpaceVariant.getGeneName() + "\" already exists");
        this.geneSpaces.add(geneSpaceVariant);
    }

    public Genotype getNextGenotype(){
        return new Genotype(geneSpaces.stream().map(GeneSpace::getNextAllele).collect(Collectors.toList()));
    }
}
