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
    private StealableCard.Resource aRequested;
    private StealableCard.Resource aOffered;

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

    public StealableCard.Resource getaRequested() {
        return aRequested;
    }

    public void setaRequested(StealableCard.Resource aRequested) {
        this.aRequested = aRequested;
    }

    public StealableCard.Resource getaOffered() {
        return aOffered;
    }

    public void setaOffered(StealableCard.Resource aOffered) {
        this.aOffered = aOffered;
    }

    public boolean isValid() {
            return isValid;
        }

    public void setValid(boolean valid) {
            isValid = valid;
        }

    public MaritimeTrade toMaritimeTrade(){
        MaritimeTrade aRet = new MaritimeTrade();
        Player aRequester = gameManager.getPlayerFromString(requester);
        aRet.setaRequester(aRequester);
        aRet.setaOfferedResource(aOffered);
        aRet.setaRequestedResource(aRequested);
        aRet.setaRequestedAmount(aAmountRequested);
        aRet.setaTradeRate(aRequester.getaMaritimeTradeRates().get(aOffered));
        return aRet;
    }
}
