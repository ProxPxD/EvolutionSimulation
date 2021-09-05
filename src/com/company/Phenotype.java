package com.company;

import java.util.HashMap;

public class Phenotype {
    private HashMap<String, Trait> traits;

    public Phenotype(){
        traits = new HashMap<>();
    }

    public void addTrait(String TraitName){
        traits.put(TraitName, new Trait(TraitName));
    }

    public Trait getTrait(String traitName) {
        return traits.get(traitName);
    }

    public boolean exist(String traitName){
        return traits.containsKey(traitName);
    }

    public void applyEffect(Effect effect){
        String targetName = effect.getTraitName();
        if (!exist(targetName))
            addTrait(targetName);

        Trait target = getTrait(targetName);
        Trait modified = effect.apply(target);

        if (modified == null)
            traits.remove(targetName);
        else
            traits.replace(targetName, modified);
    }
}
