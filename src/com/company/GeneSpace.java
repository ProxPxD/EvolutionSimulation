package com.company;

import lombok.Getter;

import java.util.List;

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

    public void setRatio(Double... ratio){
        alleles.setRatio(ratio);
    }

    public void setRatio(List<Double> ratio){
        alleles.setRatio(ratio);
    }

    public void computeNumberOfIndividualsPerAllele(int population){
        alleles.computeNumberOfIndividualsPerAllele(population);
    }

    public Allele getNextAllele(){
        return alleles.getNextAllele();
    }
}
