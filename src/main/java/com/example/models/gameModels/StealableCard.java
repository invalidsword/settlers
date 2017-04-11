package com.example.models.gameModels;

public class StealableCard{
    public enum Resource{
        SHEEP, WOOD, ORE, BRICK, WHEAT;

        public static int maxResources(){
            return 19;
        }
    }
    public enum Commodity{
        COIN, CLOTH, PAPER;

        public static int maxCommodities(){
            return 12;
        }
    }
}