package com.example.viewobjects;

import com.example.models.gameModels.GameManager;
import com.example.models.gameModels.Player;
import com.example.models.gameModels.PlayerTrade;
import com.example.models.gameModels.StealableCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * Created by Ming-PC on 4/9/2017.
 */
public class ViewPlayerTrade {

    @Autowired
    private GameManager gameManager;

    private String requester;
    private String requestee;
    private boolean isValid;
    
    private int requestedWheat;
    private int requestedOre;
    private int requestedBrick;
    private int requestedSheep;
    private int requestedWood;
    private int requestedCoin;
    private int requestedCloth;
    private int requestedPaper;
    
    private int offeredWheat;
    private int offeredOre;
    private int offeredBrick;
    private int offeredSheep;
    private int offeredWood;
    private int offeredCoin;
    private int offeredCloth;
    private int offeredPaper;

    public ViewPlayerTrade(){
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getRequestee() {
        return requestee;
    }

    public void setRequestee(String requestee) {
        this.requestee = requestee;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    public int getRequestedWheat() {
        return requestedWheat;
    }

    public void setRequestedWheat(int requestedWheat) {
        this.requestedWheat = requestedWheat;
    }

    public int getRequestedOre() {
        return requestedOre;
    }

    public void setRequestedOre(int requestedOre) {
        this.requestedOre = requestedOre;
    }

    public int getRequestedBrick() {
        return requestedBrick;
    }

    public void setRequestedBrick(int requestedBrick) {
        this.requestedBrick = requestedBrick;
    }

    public int getRequestedSheep() {
        return requestedSheep;
    }

    public void setRequestedSheep(int requestedSheep) {
        this.requestedSheep = requestedSheep;
    }

    public int getRequestedWood() {
        return requestedWood;
    }

    public void setRequestedWood(int requestedWood) {
        this.requestedWood = requestedWood;
    }

    public int getRequestedCoin() {
        return requestedCoin;
    }

    public void setRequestedCoin(int requestedCoin) {
        this.requestedCoin = requestedCoin;
    }

    public int getRequestedCloth() {
        return requestedCloth;
    }

    public void setRequestedCloth(int requestedCloth) {
        this.requestedCloth = requestedCloth;
    }

    public int getRequestedPaper() {
        return requestedPaper;
    }

    public void setRequestedPaper(int requestedPaper) {
        this.requestedPaper = requestedPaper;
    }

    public int getOfferedWheat() {
        return offeredWheat;
    }

    public void setOfferedWheat(int offeredWheat) {
        this.offeredWheat = offeredWheat;
    }

    public int getOfferedOre() {
        return offeredOre;
    }

    public void setOfferedOre(int offeredOre) {
        this.offeredOre = offeredOre;
    }

    public int getOfferedBrick() {
        return offeredBrick;
    }

    public void setOfferedBrick(int offeredBrick) {
        this.offeredBrick = offeredBrick;
    }

    public int getOfferedSheep() {
        return offeredSheep;
    }

    public void setOfferedSheep(int offeredSheep) {
        this.offeredSheep = offeredSheep;
    }

    public int getOfferedWood() {
        return offeredWood;
    }

    public void setOfferedWood(int offeredWood) {
        this.offeredWood = offeredWood;
    }

    public int getOfferedCoin() {
        return offeredCoin;
    }

    public void setOfferedCoin(int offeredCoin) {
        this.offeredCoin = offeredCoin;
    }

    public int getOfferedCloth() {
        return offeredCloth;
    }

    public void setOfferedCloth(int offeredCloth) {
        this.offeredCloth = offeredCloth;
    }

    public int getOfferedPaper() {
        return offeredPaper;
    }

    public void setOfferedPaper(int offeredPaper) {
        this.offeredPaper = offeredPaper;
    }

    public PlayerTrade toPlayerTrade(){
        PlayerTrade aRet = new PlayerTrade();

        Player aRequester = gameManager.getPlayerFromString(requester);
        Player aRequestee = gameManager.getPlayerFromString(requestee);


        HashMap<StealableCard.Resource, Integer> requestedResources = new HashMap<>();
        HashMap<StealableCard.Resource, Integer> offeredResources = new HashMap<>();
        HashMap<StealableCard.Commodity, Integer> requestedCommodities = new HashMap<>();
        HashMap<StealableCard.Commodity, Integer> offeredCommodities = new HashMap<>();

        requestedResources.put(StealableCard.Resource.BRICK, requestedBrick);
        requestedResources.put(StealableCard.Resource.WOOD, requestedWood);
        requestedResources.put(StealableCard.Resource.SHEEP, requestedSheep);
        requestedResources.put(StealableCard.Resource.ORE, requestedOre);
        requestedResources.put(StealableCard.Resource.WHEAT, requestedWheat);

        offeredResources.put(StealableCard.Resource.BRICK, offeredBrick);
        offeredResources.put(StealableCard.Resource.WOOD, offeredWood);
        offeredResources.put(StealableCard.Resource.SHEEP, offeredSheep);
        offeredResources.put(StealableCard.Resource.ORE, offeredOre);
        offeredResources.put(StealableCard.Resource.WHEAT, offeredWheat);

        requestedCommodities.put(StealableCard.Commodity.CLOTH, requestedCloth);
        requestedCommodities.put(StealableCard.Commodity.COIN, requestedCoin);
        requestedCommodities.put(StealableCard.Commodity.PAPER, requestedPaper);
        
        offeredCommodities.put(StealableCard.Commodity.CLOTH, offeredCloth);
        offeredCommodities.put(StealableCard.Commodity.COIN, offeredCoin);
        offeredCommodities.put(StealableCard.Commodity.PAPER, offeredPaper);


        aRet.setaRequester(aRequester);
        aRet.setaRequestee(aRequestee);

        aRet.setaRequestedResources(requestedResources);
        aRet.setaOfferedResources(offeredResources);
        aRet.setaRequestedCommodities(requestedCommodities);
        aRet.setaOfferedCommodities(offeredCommodities);

        return aRet;
    }
}
