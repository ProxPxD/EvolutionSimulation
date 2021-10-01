package com.company.Inside;

import lombok.Getter;

import java.util.Objects;
import java.util.function.Supplier;

public class Trait {

    @Getter
    private String type;
    @Getter String name;
    private Supplier<Double> supplier;

    public Trait(String type, String name, Supplier<Double> supplier){
        this.type = type;
        this.name = name;
        this.supplier = supplier;
    }

    public Trait(String type){
        this(type, "");
    }

    public Trait(String type, double value){
        this(type, "", value);
    }

    public Trait(String type, Supplier<Double> supplier){
        this(type, "", supplier);
    }
    public Trait(String type, String name){
        this(type, name, () -> 0.0);
    }

    public Trait(String type, String name, double value){
        this(type, name, () -> value);
    }

    public double getValue(){
        return supplier.get();
    }

    public Trait set(double value){
        supplier = () -> value;
        return this;
    }

    public Trait add(double val){
        supplier = () -> supplier.get() + val;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trait trait = (Trait) o;
        return Double.compare(trait.getValue(), getValue()) == 0 && Objects.equals(type, trait.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, supplier);
    }
}
