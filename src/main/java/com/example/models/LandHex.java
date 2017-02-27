package com.example.models;

/**
 * Created by G on 17/02/27.
 */
public class LandHex extends Hex {

    private TerrainKind aKind;
    private int aProductionNumber;
    private boolean hasMerchant;
    private boolean hasRobber;

    public LandHex(int x, int y, int pProd, TerrainKind pKind){
        super(x,y);
        aKind = pKind;
        aProductionNumber = pProd;
        hasMerchant = false;
        hasRobber = false;
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
    public TerrainKind getTerrainType() {
        return aKind;
    }


}
