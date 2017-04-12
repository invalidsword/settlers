package com.example.viewobjects;

import com.example.models.gameModels.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * Created by Ming-PC on 4/9/2017.
 */
public class ViewMaritimeTrade {
    @Autowired
    private GameManager gameManager;

    private String requester;
    private boolean isValid;
    private int aAmountRequested;
    private String aRequested;
    private String aOffered;

    public ViewMaritimeTrade(){
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public int getaAmountRequested() {
        return aAmountRequested;
    }

    public void setaAmountRequested(int aAmountRequested) {
        this.aAmountRequested = aAmountRequested;
    }


    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
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

    public MaritimeTrade toMaritimeTrade(){
        MaritimeTrade aRet = new MaritimeTrade();
        Player aRequester = gameManager.getPlayerFromString(requester);
        aRet.setaRequester(aRequester);
        aRet.setaOfferedResource(stringToResource(aOffered));
        aRet.setaRequestedResource(stringToResource(aRequested));
        aRet.setaRequestedAmount(aAmountRequested);
        aRet.setaTradeRate(aRequester.getaMaritimeTradeRates().get(aOffered));
        return aRet;
    }
}a