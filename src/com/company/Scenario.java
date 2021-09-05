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
    private List<Alleles> geneVariants;

    public Scenario(){
        startingPopulation = SimulationConstants.STARTING_POPULATION;
        geneVariants = new ArrayList<>();
    }

    public void addGeneVariants(Alleles... Alleles){
        Arrays.stream(Alleles).forEach(this::addGeneVariants);
    }

    public void addGeneVariants(Alleles geneVariant){
        if (this.geneVariants.stream().map(Alleles::getGeneName).anyMatch(n -> n.equals(geneVariant.getGeneName())))
            throw new IllegalArgumentException("Allele group of the name \"" + geneVariant.getGeneName() + "\" already exists");
        this.geneVariants.add(geneVariant);
    }
}
