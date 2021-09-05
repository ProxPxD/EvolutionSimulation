package com.company;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GeneSpace {

    @Getter
    String geneName;
    private AllelePrevalence alleles = new AllelePrevalence();

    public GeneSpace(String name){
        this.geneName = name;
    }

    public Allele createAllele(String alleleName){
        Allele allele = new Allele(geneName, alleleName);
        alleles.addAllele(allele);
        return allele;
    }

    public void setEquinumerousRatio(){
        alleles.setEquinumerousRatio();
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
