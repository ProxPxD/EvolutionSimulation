package com.company;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Trait {

    @Getter
    private String name;
    @Getter @Setter
    private double value;

    public Trait(String name){
        this.name = name;
    }

    public Trait(String name, double value){
        this(name);
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trait trait = (Trait) o;
        return Double.compare(trait.value, value) == 0 && Objects.equals(name, trait.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }
}
