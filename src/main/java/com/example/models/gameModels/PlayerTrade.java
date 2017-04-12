package com.example.models.gameModels;

import java.util.HashMap;

public class PlayerTrade implements Trade{
    private Player aRequester;
    private Player aRequestee;

    public void setaRequester(Player aRequester) {
        this.aRequester = aRequester;
    }

    public void setaRequestee(Player aRequestee) {
        this.aRequestee = aRequestee;
    }

    public void setaRequestedResources(HashMap<StealableCard.Resource, Integer> aRequestedResources) {
        this.aRequestedResources = aRequestedResources;
    }

    public void setaOfferedResources(HashMap<StealableCard.Resource, Integer> aOfferedResources) {
        this.aOfferedResources = aOfferedResources;
    }

    public void setaRequestedCommodities(HashMap<StealableCard.Commodity, Integer> aRequestedCommodities) {
        this.aRequestedCommodities = aRequestedCommodities;
    }

    public void setaOfferedCommodities(HashMap<StealableCard.Commodity, Integer> aOfferedCommodities) {
        this.aOfferedCommodities = aOfferedCommodities;
    }

    private HashMap<StealableCard.Resource, Integer> aRequestedResources;
    private HashMap<StealableCard.Resource, Integer> aOfferedResources;
    private HashMap<StealableCard.Commodity, Integer> aRequestedCommodities;
    private HashMap<StealableCard.Commodity, Integer> aOfferedCommodities;

    public PlayerTrade(){
    }

    public PlayerTrade(Player pRequester, Player pRequestee,
    HashMap<StealableCard.Resource, Integer> pRequestedResources,
    HashMap<StealableCard.Resource, Integer> pOfferedResources,
    HashMap<StealableCard.Commodity, Integer> pRequestedCommodities,
    HashMap<StealableCard.Commodity, Integer> pOfferedCommodities) {
        aRequester = pRequester;
        aRequestee = pRequestee;
        aRequestedResources = pRequestedResources;
        aOfferedResources = pOfferedResources;
        aRequestedCommodities = pRequestedCommodities;
        aOfferedCommodities = pOfferedCommodities;
    }

    public void execute(){
        for (StealableCard.Resource resource : StealableCard.Resource.values()){
            aRequester.removeResource(resource, aOfferedResources.get(resource));
            aRequestee.removeResource(resource, aRequestedResources.get(resource));
            aRequester.addResource(resource, aRequestedResources.get(resource));
            aRequestee.addResource(resource, aOfferedResources.get(resource));
        }
        for (StealableCard.Commodity commodity : StealableCard.Commodity.values()){
            aRequester.removeCommodity(commodity, aOfferedCommodities.get(commodity));
            aRequestee.removeCommodity(commodity, aRequestedCommodities.get(commodity));
            aRequester.addCommodity(commodity, aRequestedCommodities.get(commodity));
            aRequestee.addCommodity(commodity, aOfferedCommodities.get(commodity));        }
    }

    public Player getaRequester() {
        return aRequester;
    }

    public Player getaRequestee() {
        return aRequestee;
    }

    public HashMap<StealableCard.Resource, Integer> getaRequestedResources() {
        return aRequestedResources;
    }

    public HashMap<StealableCard.Resource, Integer> getaOfferedResources() {
        return aOfferedResources;
    }

    public HashMap<StealableCard.Commodity, Integer> getaRequestedCommodities() {
        return aRequestedCommodities;
    }

    public HashMap<StealableCard.Commodity, Integer> getaOfferedCommodities() {
        return aOfferedCommodities;
    }
}
