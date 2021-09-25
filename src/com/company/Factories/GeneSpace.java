package com.company.Factories;

import com.company.Inside.Allele;
import lombok.Getter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class GeneSpace {

    @Getter
    String geneName;
    private AllelePrevalence alleles = new AllelePrevalence();

    public GeneSpace(String name){
        this.geneName = name;
    }

    public Allele createAllele(String alleleName){
        return alleles.addAllele(new Allele(geneName, alleleName));
    }

    public void setEquinumerousRatio(){
        alleles.setEquinumerousPercentage();
    }

    public void setRatio(Integer... ratio){
        alleles.setRatio(Arrays.stream(ratio).map(Integer::doubleValue).collect(Collectors.toList()));
    }
    public void setRatio(Double... ratio){
        alleles.setRatio(ratio);
    }

    public void computeNumberOfIndividualsPerAllele(int population){
        alleles.computeNumberOfIndividualsPerAllele(population);
    }

    public Allele getNextAllele(){
        return alleles.getNextAllele();
    }
}
