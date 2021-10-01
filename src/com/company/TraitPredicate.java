package com.company;

import com.company.Inside.Trait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import static com.company.TraitPredicate.ConditionType.*;

public class TraitPredicate implements Predicate<Creature> {

    public enum ConditionType {TRUE, FALSE, EQUAL, NOT_EQUAL, EXIST, NOT_EXIST, GT, LT, GE, LE, AND, OR, NOT}

    public static TraitPredicate andCreate(TraitPredicate... conditions){
        return new TraitPredicate(AND, conditions);
    }

    public static TraitPredicate orCreate(TraitPredicate... conditions){
        return new TraitPredicate(OR, conditions);
    }

    public static TraitPredicate makeTrue(Trait trait){
        return new TraitPredicate(TRUE, trait);
    }

    public static TraitPredicate makeFalse(Trait trait){
        return new TraitPredicate(FALSE, trait);
    }

    public static TraitPredicate makeEqual(Trait trait){
        return new TraitPredicate(EQUAL, trait);
    }

    public static TraitPredicate makeNotEqual(Trait trait){
        return new TraitPredicate(NOT_EQUAL, trait);
    }

    public static TraitPredicate makeGreaterThan(Trait trait){
        return new TraitPredicate(GT, trait);
    }

    public static TraitPredicate makeLessThan(Trait trait){
        return new TraitPredicate(LT, trait);
    }

    public static TraitPredicate makeGreaterOrEqual(Trait trait){
        return new TraitPredicate(GE, trait);
    }

    public static TraitPredicate makeLessOrEqual(Trait trait){
        return new TraitPredicate(LE, trait);
    }

    public static TraitPredicate makeExist(Trait trait){
        return new TraitPredicate(EXIST, trait);
    }

    public static TraitPredicate makeNotExist(Trait trait){
        return new TraitPredicate(NOT_EXIST, trait);
    }

    /// Without Trait class

    public static TraitPredicate makeTrue(String traitName){
        return new TraitPredicate(TRUE, new Trait(traitName));
    }

    public static TraitPredicate makeFalse(String traitName){
        return new TraitPredicate(FALSE, new Trait(traitName));
    }

    public static TraitPredicate makeEqual(String traitName){
        return new TraitPredicate(EQUAL, new Trait(traitName));
    }

    public static TraitPredicate makeEqual(String traitName, double value){
        return new TraitPredicate(EQUAL, new Trait(traitName, value));
    }

    public static TraitPredicate makeNotEqual(String traitName, double value){
        return new TraitPredicate(NOT_EQUAL, new Trait(traitName, value));
    }

    public static TraitPredicate makeGreaterThan(String traitName, double value){
        return new TraitPredicate(GT, new Trait(traitName, value));
    }

    public static TraitPredicate makeLessThan(String traitName, double value){
        return new TraitPredicate(LT, new Trait(traitName, value));
    }

    public static TraitPredicate makeGreaterOrEqual(String traitName, double value){
        return new TraitPredicate(GE, new Trait(traitName, value));
    }

    public static TraitPredicate makeLessOrEqual(String traitName, double value){
        return new TraitPredicate(LE, new Trait(traitName, value));
    }


    public static TraitPredicate makeExist(String traitName){
        return new TraitPredicate(EXIST, new Trait(traitName));
    }

    public static TraitPredicate makeNotExist(String traitName){
        return new TraitPredicate(NOT_EXIST, new Trait(traitName));
    }


    private Trait trait;
    private ConditionType type = TRUE;
    private List<TraitPredicate> subConditions = new ArrayList<>();


    private TraitPredicate(ConditionType type, Trait trait){
        this.type = type;
        this.trait = trait;
    }

    private TraitPredicate(ConditionType type, TraitPredicate... conditions){
        this.type = type;
        subConditions.addAll(Arrays.asList(conditions));
    }

    private TraitPredicate(TraitPredicate condition1, ConditionType type, TraitPredicate... conditions){
        this(type, conditions);
        subConditions.add(condition1);
    }

    public TraitPredicate and(TraitPredicate... traitPredicate){
        return new TraitPredicate(this, AND, traitPredicate);
    }

    public TraitPredicate or(TraitPredicate... traitPredicate){
        return new TraitPredicate(this, OR, traitPredicate);
    }

    public TraitPredicate not(){
        return new TraitPredicate(NOT, this);
    }


    @Override
    public boolean test(Creature creature) {
        return isSatisfiedBy(creature);
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