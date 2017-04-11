package com.example.models.gameModels;

import java.util.HashMap;

public class MaritimeTrade implements Trade {
    private Player aRequester;
    private int aTradeRate;
    private int aRequestedAmount;
    private StealableCard.Resource aRequestedResource;
    private StealableCard.Resource aOfferedResource;

    public MaritimeTrade(){
    }

    public void execute(){
        aRequester.removeResource(aOfferedResource, aRequestedAmount * aTradeRate);
        aRequester.addResource(aRequestedResource, aRequestedAmount);
    }

    public Player getaRequester() {
        return aRequester;
    }

    public void setaRequester(Player aRequester) {
        this.aRequester = aRequester;
    }

    public int getaTradeRate() {
        return aTradeRate;
    }

    public void setaTradeRate(int aTradeRate) {
        this.aTradeRate = aTradeRate;
    }

    public int getaRequestedAmount() {
        return aRequestedAmount;
    }

    public void setaRequestedAmount(int aRequestedAmount) {
        this.aRequestedAmount = aRequestedAmount;
    }

    public StealableCard.Resource getaRequestedResource() {
        return aRequestedResource;
    }

    public void setaRequestedResource(StealableCard.Resource aRequestedResource) {
        this.aRequestedResource = aRequestedResource;
    }

    public StealableCard.Resource getaOfferedResource() {
        return aOfferedResource;
    }

    public void setaOfferedResource(StealableCard.Resource aOfferedResource) {
        this.aOfferedResource = aOfferedResource;
    }
}
