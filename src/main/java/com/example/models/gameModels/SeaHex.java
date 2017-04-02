package com.example.models.gameModels;

/**
 * Created by G on 17/02/27.
 */
public class SeaHex extends Hex {

    public SeaHex(String pId){
        super(pId);
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Sea;
    }


}
