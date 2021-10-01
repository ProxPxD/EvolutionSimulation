package com.company;

import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

public class SimulationConstants {

    public static final Supplier<Double> randDouble = () -> new Random().nextDouble();
    public static final Function<Integer, Integer> randIndex = size -> new Random().nextInt(size);

    public static final int STARTING_POPULATION = 100;
    public static final int DAY_LIMIT = 400;
    public static final int POPULATION_LIMIT = 600;
    public static final int INFINITE_POPULATION_LIMIT = -1;

    public static final String DEATH_RATE_NAME = "Death Rate";
    public static final String DUPLICATION_RATE_NAME = "Duplication Rate";
    public static final String DUEL_NAME = "Duel";

}
