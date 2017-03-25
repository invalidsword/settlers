package com.example.models;

import java.util.ArrayList;
import java.util.List;

abstract class Hex {
    int aX;
    int aY;
    List<Hex> aNeighbours;
    TerrainType aTerrainType;

    public enum TerrainType{
        DESERT, PASTURE, FOREST, MOUNTAINS, HILLS, FIELDS, GOLDMINE, QUARRY, SEA
    }

    boolean isAdjacent(Hex pHex){
        return aNeighbours.contains(pHex);
    }

    List<Integer> getCoordinates(){
        List<Integer> retList = new ArrayList<>();
        retList.add(aX);
        retList.add(aY);
        return retList;
    }

    TerrainType getTerrainType(){
        return aTerrainType;
    }

}

