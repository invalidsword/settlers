package com.example.models.gameModels;

/**
 * Created by G on 17/02/27.
 */
public class LandHex extends Hex {

    private TerrainType aTerrainType;
    private int aProductionNumber;
    private boolean hasMerchant;
    private boolean hasRobber;

    public LandHex(String pId, int pProductionNumber, TerrainType pTerrainType){
        super(pId);
        aTerrainType = pTerrainType;
        aProductionNumber = pProductionNumber;
        hasMerchant = false;
        hasRobber = false;
    }

    public int getProductionNumber(){
        return aProductionNumber;
    }

    public boolean getRobberFlag(){
        return hasRobber;
    }

    public void updateRobberFlag()
    {
        if(hasRobber==true)
            hasRobber = false;
        else
            hasRobber = true;
    }

    public boolean getMerchantFlag(){
        return hasMerchant;
    }

    public void updateMerchantFlag()
    {
        if(hasMerchant==true)
            hasMerchant = false;
        else
            hasMerchant = true;
    }

    @Override
    public TerrainType getTerrainType() {
        return aTerrainType;
    }


}
