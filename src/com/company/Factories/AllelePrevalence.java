package com.company.Factories;

import com.company.Inside.Allele;
import com.company.NotPositiveException;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AllelePrevalence {

    private List<Allele> alleles = new ArrayList<>();
    private List<Double> percentages = new ArrayList<>();
    private List<Integer> numberOfIndividuals = new ArrayList<>();

    public AllelePrevalence() {

    }

    public Allele addAllele(Allele allele){
        alleles.add(allele);
        return allele;
    }

    public void setEquinumerousPercentage(){
        setRatio(1.0);
    }

    public void setRatio(Double... ratio){
        setRatio(Arrays.asList(ratio));
    }

    public void setRatio(List<Double> ratios){
        if (ratios.stream().anyMatch(p -> p < 0))
            throw new NotPositiveException("Ratio");

        percentages.addAll(ratios);
        AdjustLackingPercentages();
        normalisePercentages();
    }

    private void AdjustLackingPercentages(){
        double sum = sumPercentages();
        Double mean = percentages.size() == 0 ? 0.0 : sum / percentages.size();
        percentages.addAll(Collections.nCopies(alleles.size() - percentages.size(), mean));
    }

    private void normalisePercentages(){
        percentages = percentages.stream().map(r -> r / sumPercentages()).collect(Collectors.toList());;
    }

    private double sumPercentages(){
        return percentages.stream().reduce(0.0, Double::sum);
    }

    public void computeNumberOfIndividualsPerAllele(int population){
        if (alleles.size() > population)
            throw new IllegalArgumentException("The population is to small to fit the allele prevalence");
        if (percentages.isEmpty())
            setEquinumerousPercentage();

        numberOfIndividuals = percentages.stream()
                .map(p -> (int) (population * p))
                .map(n -> n == 0 ? 1 : n)
                .collect(Collectors.toList());
        adjustNumberOfIndividuals(population);
    }

    private void adjustNumberOfIndividuals(int population) {
        while (numberOfIndividuals.stream().reduce(0, Integer::sum) > population)
            removeTheMostNumerous();
    }

    private void removeTheMostNumerous(){
        int biggest = numberOfIndividuals.stream().max(Comparator.naturalOrder()).get();
        int i = numberOfIndividuals.indexOf(biggest);
        numberOfIndividuals.set(i, numberOfIndividuals.get(i) - 1);
    }

    public Allele getNextAllele(){
        int index = IntStream.range(0, alleles.size())
                .filter(i -> numberOfIndividuals.get(i) != 0)
                .findFirst().orElseThrow(() -> new IllegalStateException("There is no allele left"));
        numberOfIndividuals.set(index, numberOfIndividuals.get(index) - 1);
        return alleles.get(index);
    }

}
