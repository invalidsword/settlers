package com.example.models.gameModels;

import javax.persistence.Embeddable;


public interface Unit {
    public enum Building{
        SETTLEMENT, CITY;

        public static int maxBuildings(){
            return 4;
        }
    }

    public enum Transport{
        ROAD, SHIP;

        public static int maxTransports(){
            return 15;
        }
    }

    public enum Knight{
        BASIC, STRONG, MIGHTY;

        public static int maxKnights(){
            return 2;
        }

        public int strength(){
            return ordinal() + 1;
        }
    }
}
