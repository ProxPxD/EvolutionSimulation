package com.company;

import lombok.Getter;

import java.util.Objects;
import java.util.function.Supplier;

public class Trait {

    @Getter
    private String name;
    private Supplier<Double> supplier;

    public Trait(String name, Supplier<Double> supplier){
        this.name = name;
        this.supplier = supplier;
    }

    public Trait(String name){
        this(name, () -> 0.0);
    }

    public Trait(String name, double value){
        this(name, () -> value);
    }

    public double getValue(){
        return supplier.get();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trait trait = (Trait) o;
        return Double.compare(trait.getValue(), getValue()) == 0 && Objects.equals(name, trait.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, supplier);
    }
}
