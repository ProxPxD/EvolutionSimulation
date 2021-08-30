package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static com.company.TraitCondition.ConditionType.*;

public class TraitCondition {
    public enum ConditionType {TRUE, FALSE, EQUAL, NOT_EQUAL, GT, LT, GE, LE, AND, OR, NOT}

    private Trait trait;
    private ConditionType type = TRUE;
    private List<TraitCondition> subConditions = new ArrayList<>();

    public static TraitCondition andCreate(TraitCondition... conditions){
        return new TraitCondition(AND, conditions);
    }

    public static TraitCondition orCreate(TraitCondition... conditions){
        return new TraitCondition(OR, conditions);
    }

    public TraitCondition(ConditionType type, Trait trait){
        this.type = type; //TODO make type checking
        this.trait = trait;
    }

    public TraitCondition(ConditionType type, TraitCondition ... conditions){
        this.type = type;
        subConditions.addAll(Arrays.asList(conditions));
    }

    private TraitCondition(TraitCondition condition1, ConditionType type, TraitCondition ... conditions){
        this(type, conditions);
        subConditions.add(condition1);
    }


    public TraitCondition and(TraitCondition ... traitCondition){
        return new TraitCondition(this, AND, traitCondition);
    }

    public TraitCondition or(TraitCondition ... traitCondition){
        return new TraitCondition(this, OR, traitCondition);
    }

    public TraitCondition not(){
        return new TraitCondition(NOT, this);
    }

    public boolean isSatisfiedBy(Creature creature){
        return isSatisfiedBy(creature, type);
    }

    private boolean isSatisfiedBy(Creature creature, ConditionType type){
        boolean isSatisfied;
        switch (type){
            case TRUE -> isSatisfied = true;
            case EQUAL -> isSatisfied = creature.getTraits().getTrait(trait.getName()).getValue() == trait.getValue();
            case NOT_EQUAL -> isSatisfied = !isSatisfiedBy(creature, EQUAL);

            case GT -> isSatisfied = creature.getTraits().getTrait(trait.getName()).getValue() > trait.getValue();
            case GE -> isSatisfied = isSatisfiedBy(creature, EQUAL) || isSatisfiedBy(creature, GT);
            case LT -> isSatisfied = !isSatisfiedBy(creature, GE);
            case LE -> isSatisfied = !isSatisfiedBy(creature, GT);

            case AND -> isSatisfied = areSatisfiedBy(creature, true, (acc, x) -> acc && x);
            case OR -> isSatisfied = areSatisfiedBy(creature, false, (acc, x) -> acc || x);
            case FALSE -> isSatisfied = false;
            default -> throw new IllegalStateException(type.name() + " is not a legal type");
        }
        return isSatisfied;
    }

    private boolean areSatisfiedBy(Creature creature, Boolean initial, BiFunction<Boolean, Boolean, Boolean> condition){
        return subConditions.stream().map(c -> c.isSatisfiedBy(creature)).reduce(initial, condition::apply);
    }

}
