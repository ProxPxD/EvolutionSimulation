package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AllelePrevalence {

    private List<Allele> alleles;
    private List<Double> percentage;
    private List<Integer> numberOfIndividuals;

    public AllelePrevalence(){
        alleles = new ArrayList<>();
        percentage = new ArrayList<>();
        numberOfIndividuals = new ArrayList<>();
    }

    public void addAllele(Allele allele){
        alleles.add(allele);
    }

    public void setEquinumerousRatio(){
        setRatio();
    }

    public void setRatio(Double... ratio){
        setRatio(Arrays.asList(ratio));
    }

    public void setRatio(List<Double> ratio){
        if (ratio.stream().anyMatch(p -> p < 0))
            throw new NotPositiveException("Ratio");
        double sum = ratio.stream().reduce(0.0, Double::sum);
        adjustRatio(ratio, sum);
        addLackingPercentages(ratio, sum);
        setPrevalenceSafely(ratio);
    }

    private void adjustRatio(List<Double> ratio, double sum){
        double mean = sum / ratio.size();
        while (ratio.size() < alleles.size())
            ratio.add(mean);
    }

    private void addLackingPercentages(List<Double> percentages, double sum){
        double lackingAmount = 1 - sum;
        double spreadAmount = lackingAmount / (alleles.size() - percentages.size());
        while (percentages.size() < alleles.size())
            percentages.add(spreadAmount);
    }

    private void setPrevalenceSafely(List<Double> percentages){
        percentage.clear();
        percentage.addAll(percentages);
    }

    public void computeNumberOfIndividualsPerAllele(int population){
        numberOfIndividuals = percentage.stream().map(p -> (int) (population * p)).collect(Collectors.toList());
        while (numberOfIndividuals.stream().anyMatch(a -> a == 0)){
           adjustForZeroes();
        }
    }

    private void adjustForZeroes(){
        int zeroIndex = numberOfIndividuals.indexOf(0);
        int biggest = numberOfIndividuals.stream().reduce(-1, Integer::max);
        int greatestIndex = numberOfIndividuals.indexOf(biggest);
        numberOfIndividuals.set(greatestIndex, biggest - 1);
        numberOfIndividuals.set(zeroIndex, 1);
    }

    public Allele getNextAllele(){
        Allele next;
        for (int i = 0; i < numberOfIndividuals.size(); i++) {
            if (numberOfIndividuals.get(i) > 0){
                numberOfIndividuals.set(i, numberOfIndividuals.get(i) - 1);
                next = alleles.get(i);
                return next;
            }
        }
        throw new IllegalStateException("There is no allele left");
    }

}
