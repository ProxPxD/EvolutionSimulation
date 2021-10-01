package com.company;

import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.HashMap;


public class PayoutMatrix {
    private String trait;
    private HashMap<String, HashMap<String, SimpleEntry<Double, Double>>> matrix;

    PayoutMatrix(String traitName, String... traitTypes){
        trait = traitName;
        matrix = new HashMap<>();
        HashMap<String, SimpleEntry<Double, Double>> row = new HashMap<>();
        Arrays.stream(traitTypes).forEach(type -> row.put(type, new SimpleEntry<>(0.0, 0.0)));
        Arrays.stream(traitTypes).forEach(type -> matrix.put(type, new HashMap<>(row)));
    }

    public void set(String trait1, String trait2, double value1, double value2){
        matrix.get(trait1).put(trait2, new SimpleEntry<>(value1, value2));
    }

    public SimpleEntry<Double, Double> get(Creature creature1, Creature creature2){
        return get(creature1.getTrait(trait).getName(), creature2.getTrait(trait).getName());
    }

    public SimpleEntry<Double, Double> get(String trait1, String trait2){
        return matrix.get(trait1).get(trait2);
    }

    public int size(){
        return matrix.size();
    }
}
