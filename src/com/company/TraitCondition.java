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
            case FALSE -> isSatisfied = false;
            default -> throw new IllegalStateException(type.name() + " is not a legal type");
        }
        return isSatisfied;
    }

    private boolean areSatisfiedBy(Creature creature, Boolean initial, BiFunction<Boolean, Boolean, Boolean> condition){
        return subConditions.stream().map(c -> c.isSatisfiedBy(creature)).reduce(initial, condition::apply);
    }
}
