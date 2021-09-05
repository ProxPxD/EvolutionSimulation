package com.company;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Scenario {

    @Setter @Getter
    private int startingPopulation;
    @Getter
    private List<GeneAllelesSpace> geneAllelesSpaces;

    public Scenario(){
        startingPopulation = SimulationConstants.STARTING_POPULATION;
        geneAllelesSpaces = new ArrayList<>();
    }

    public void addGeneSpace(GeneAllelesSpace... geneAllelesSpaces){
        Arrays.stream(geneAllelesSpaces).forEach(this::addGeneSpace);
    }

    public void addGeneSpace(GeneAllelesSpace geneAllelesSpaceVariant){
        if (this.geneAllelesSpaces.stream().map(GeneAllelesSpace::getGeneName).anyMatch(n -> n.equals(geneAllelesSpaceVariant.getGeneName())))
            throw new IllegalArgumentException("Allele group of the name \"" + geneAllelesSpaceVariant.getGeneName() + "\" already exists");
        this.geneAllelesSpaces.add(geneAllelesSpaceVariant);
    }
}
