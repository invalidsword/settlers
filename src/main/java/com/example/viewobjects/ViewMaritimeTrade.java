package com.example.viewobjects;

import com.example.models.gameModels.MaritimeTrade;
import com.example.models.gameModels.Player;
import com.example.models.gameModels.StealableCard;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Ming-PC on 4/9/2017.
 */
public class ViewMaritimeTrade {
    @Autowired
    private boolean isValid;
    private String aRequested;

    public String getaRequested() {
        return aRequested;
    }

    public void setaRequested(String aRequested) {
        this.aRequested = aRequested;
    }

    private String aOffered;

    public String getaOffered() {
        return aOffered;
    }

    public void setaOffered(String aOffered) {
        this.aOffered = aOffered;
    }

    public ViewMaritimeTrade(){
    }

    public boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(boolean valid) {
        isValid = valid;
    }

    public StealableCard.Resource stringToResource(String pString){
        switch (pString){
            case "Wood":
                return StealableCard.Resource.WOOD;
            case "Wheat":
                return StealableCard.Resource.WHEAT;
            case "Sheep":
                return StealableCard.Resource.SHEEP;
            case "Brick":
                return StealableCard.Resource.BRICK;
            case "Ore":
                return StealableCard.Resource.ORE;
        }
        return null;
    }

    public MaritimeTrade toMaritimeTrade(Player pPlayer){
        MaritimeTrade aRet = new MaritimeTrade();
        aRet.setaRequester(pPlayer);
        aRet.setaOfferedResource(stringToResource(aOffered));
        aRet.setaRequestedResource(stringToResource(aRequested));
     //   aRet.setaTradeRate(pPlayer.getaMaritimeTradeRates().get(aOffered));
        return aRet;
    }
}