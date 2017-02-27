package com.example.models;

/**
 * Created by G on 17/02/27.
 */
public class SeaHex extends Hex {

    private boolean hasPirate;

    public SeaHex(int x, int y){
        super(x,y);
        hasPirate = false;
    }

    public boolean getPirateFlag(){
        return hasPirate;
    }

    public void updatePirateFlag()
    {
        if(hasPirate==true)
            hasPirate = false;
        else
            hasPirate = true;
    }

    @Override
    public TerrainKind getTerrainType() {
        return TerrainKind.Sea;
    }


}
