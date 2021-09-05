package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllelePrevalence {

    private List<Allele> alleles;
    private List<Double> prevalence;

    public AllelePrevalence(){
        alleles = new ArrayList<>();
        prevalence = new ArrayList<>();
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
        prevalence.clear();
        prevalence.addAll(percentages);
    }
}
