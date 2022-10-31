package com.sep3yg9.assignment2.misc;


import com.sep3yg9.assignment2.model.Animal;
import com.sep3yg9.assignment2.model.Part;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateParts
{
    //static method, takes animal, returns parts
    //animal repo -> create parts -> parts repo

    private static final Map<Long, Part> parts = new HashMap<>();

    public enum Parts{
        TORSO(50), FRONT_LEGS(25), BACK_LEGS(35);

        private int weight;
        Parts(int weight) {
            this.weight = weight;
        }

    }

    public static void cutIntoParts(Animal animal){
        long id = 1L;
        if(!parts.isEmpty()){
            id = (long) parts.keySet().toArray()[parts.keySet().size() -1]+1;
        }
        parts.put(id, new Part(id,animal.getRegNumber(),Parts.TORSO.name(),(Parts.TORSO.weight * animal.getWeight())/100));
        id++;
        parts.put(id, new Part(id,animal.getRegNumber(),Parts.FRONT_LEGS.name(),(Parts.FRONT_LEGS.weight * animal.getWeight())/100));
        id++;
        parts.put(id, new Part(id,animal.getRegNumber(),Parts.BACK_LEGS.name(), (Parts.BACK_LEGS.weight * animal.getWeight())/100));
    }



}
