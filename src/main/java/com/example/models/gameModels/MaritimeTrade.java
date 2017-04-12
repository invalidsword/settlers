package com.example.models.gameModels;

public class MaritimeTrade implements Trade {
    private Player aRequester;
    private StealableCard.Resource aRequestedResource;
    private StealableCard.Resource aOfferedResource;

    public MaritimeTrade(){
    }

    public void execute(){
        aRequester.removeResource(aOfferedResource, 4);
        aRequester.addResource(aRequestedResource, 1);
    }

    public Player getaRequester() {
        return aRequester;
    }

    public void setaRequester(Player aRequester) {
        this.aRequester = aRequester;
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