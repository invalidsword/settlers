package com.example.models.gameModels;

import javax.persistence.Entity;

/**
 * Created by G on 17/02/27.
 */
@Entity
public class LandHex extends Hex {

    public LandHex(){}

    @Override
    public String toString() {
        return "LandHex{" +
                "aTerrainType=" + aTerrainType +
                ", aProductionNumber=" + aProductionNumber +
                ", hasMerchant=" + hasMerchant +
                ", hasRobber=" + hasRobber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LandHex)) return false;

        LandHex landHex = (LandHex) o;

        if (aProductionNumber != landHex.aProductionNumber) return false;
        if (hasMerchant != landHex.hasMerchant) return false;
        if (hasRobber != landHex.hasRobber) return false;
        return aTerrainType == landHex.aTerrainType;
    }

    @Override
    public int hashCode() {
        int result = aId.hashCode();
        return result;
    }

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
