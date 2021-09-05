package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

import static com.company.TraitCondition.ConditionType.*;

public class TraitCondition {
    public enum ConditionType {TRUE, FALSE, EQUAL, NOT_EQUAL, EXIST, NOT_EXIST, GT, LT, GE, LE, AND, OR, NOT}

    public static TraitCondition andCreate(TraitCondition... conditions){
        return new TraitCondition(AND, conditions);
    }

    public static TraitCondition orCreate(TraitCondition... conditions){
        return new TraitCondition(OR, conditions);
    }

    public static TraitCondition makeTrue(Trait trait){
        return new TraitCondition(TRUE, trait);
    }

    public static TraitCondition makeFalse(Trait trait){
        return new TraitCondition(FALSE, trait);
    }

    public static TraitCondition makeEqual(Trait trait){
        return new TraitCondition(EQUAL, trait);
    }

    public static TraitCondition makeNotEqual(Trait trait){
        return new TraitCondition(NOT_EQUAL, trait);
    }

    public static TraitCondition makeGreaterThan(Trait trait){
        return new TraitCondition(GT, trait);
    }

    public static TraitCondition makeLessThan(Trait trait){
        return new TraitCondition(LT, trait);
    }

    public static TraitCondition makeGreaterOrEqual(Trait trait){
        return new TraitCondition(GE, trait);
    }

    public static TraitCondition makeLessOrEqual(Trait trait){
        return new TraitCondition(LE, trait);
    }

    public static TraitCondition makeExist(Trait trait){
        return new TraitCondition(EXIST, trait);
    }

    public static TraitCondition makeNotExist(Trait trait){
        return new TraitCondition(NOT_EXIST, trait);
    }

    /// Without Trait class

    public static TraitCondition makeFalse(String traitName){
        return new TraitCondition(FALSE, new Trait(traitName));
    }

    public static TraitCondition makeEqual(String traitName){
        return new TraitCondition(EQUAL, new Trait(traitName));
    }

    public static TraitCondition makeEqual(String traitName, double value){
        return new TraitCondition(EQUAL, new Trait(traitName, value));
    }

    public static TraitCondition makeNotEqual(String traitName, double value){
        return new TraitCondition(NOT_EQUAL, new Trait(traitName, value));
    }

    public static TraitCondition makeGreaterThan(String traitName, double value){
        return new TraitCondition(GT, new Trait(traitName, value));
    }

    public static TraitCondition makeLessThan(String traitName, double value){
        return new TraitCondition(LT, new Trait(traitName, value));
    }

    public static TraitCondition makeGreaterOrEqual(String traitName, double value){
        return new TraitCondition(GE, new Trait(traitName, value));
    }

    public static TraitCondition makeLessOrEqual(String traitName, double value){
        return new TraitCondition(LE, new Trait(traitName, value));
    }


    public static TraitCondition makeExist(String traitName){
        return new TraitCondition(EXIST, new Trait(traitName));
    }

    public static TraitCondition makeNotExist(String traitName){
        return new TraitCondition(NOT_EXIST, new Trait(traitName));
    }


    private Trait trait;
    private ConditionType type = TRUE;
    private List<TraitCondition> subConditions = new ArrayList<>();


    private TraitCondition(ConditionType type, Trait trait){
        this.type = type;
        this.trait = trait;
    }

    private TraitCondition(ConditionType type, TraitCondition ... conditions){
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
            case FALSE -> isSatisfied = false;
            case EQUAL -> isSatisfied = creature.getTrait(trait).equals(trait);
            case NOT_EQUAL -> isSatisfied = !creature.getTrait(trait).equals(trait);

            case GT -> isSatisfied = creature.getTrait(trait).getValue() > trait.getValue();
            case GE -> isSatisfied = creature.getTrait(trait).getValue() >= trait.getValue();
            case LT -> isSatisfied = creature.getTrait(trait).getValue() < trait.getValue();
            case LE -> isSatisfied = creature.getTrait(trait).getValue() <= trait.getValue();

            case EXIST -> isSatisfied = creature.getTraits().exist(trait.getName());
            case NOT_EXIST -> isSatisfied = !creature.getTraits().exist(trait.getName());

            case AND -> isSatisfied = areSatisfiedBy(creature, true, (acc, x) -> acc && x);
            case OR -> isSatisfied = areSatisfiedBy(creature, false, (acc, x) -> acc || x);
            default -> throw new IllegalStateException(type.name() + " is not a legal type");
        }
        return isSatisfied;
    }

    private boolean areSatisfiedBy(Creature creature, Boolean initial, BiFunction<Boolean, Boolean, Boolean> condition){
        return subConditions.stream().map(c -> c.isSatisfiedBy(creature)).reduce(initial, condition::apply);
    }
}