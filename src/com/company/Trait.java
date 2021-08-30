package com.company;

import lombok.Getter;
import lombok.Setter;

public class Trait {

    @Getter
    private String name;
    @Getter @Setter
    private double value;

    public Trait(String name){
        this.name = name;
    }
}
