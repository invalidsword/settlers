package com.example.models.gameModels;

import javax.persistence.Entity;

/**
 * Created by G on 17/02/27.
 */
@Entity
public class SeaHex extends Hex {
    @Override
    public String toString() {
        return "SeaHex{}";
    }

    public SeaHex(){}

    public SeaHex(String pId){
        super(pId);
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Sea;
    }


}
