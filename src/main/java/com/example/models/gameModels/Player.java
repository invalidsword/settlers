package com.example.models.gameModels;
import java.util.HashMap;
import java.util.Queue;

/**
 * Created by G on 17/02/28.
 */
public class Player {
    private final String aColor;
    private final String aUsername;
    private int aVPs;
    private int aGold;
    private int aRouteLength;
    private boolean aMerchant;
    private boolean aLongestTradeRoute;
    private boolean aAqueduct;
    private boolean aFortress;
    private boolean aTradingHouse;
    private HashMap<ResourceCard.ResourceType, Queue<ResourceCard>> aResourceCards;
    private HashMap<CommodityCard.CommodityType, Queue<CommodityCard>> aCommodityCards;
    private Queue<City> aCities;
    private Queue<Road> aRoads;
    private Queue<Ship> aShips;
    private Queue<Settlement> aSettlements;
    private Queue<BasicKnight> aBasicKnights;
    private Queue<StrongKnight> aStrongKnights;
    private Queue<MightyKnight> aMightyKnights;
    private Queue<Wall> aWalls;

    public Player(String pUsername, String pColor){
        aUsername = pUsername;
        aColor = pColor;
        aVPs = 0;
        aGold = 0;
        aRouteLength = 0;
        aMerchant = false;
        aLongestTradeRoute = false;
        aAqueduct = false;
        aFortress = false;
        aTradingHouse = false;
        aResourceCards = new HashMap<ResourceCard.ResourceType, Queue<ResourceCard>>();
        aCommodityCards = new HashMap<CommodityCard.CommodityType, Queue<CommodityCard>>();

    }

    public enum Color{
        Blue, Orange, Red, White
    }

    public String getColor(){
        return aColor;
    }

    public String getUsername(){ return aUsername; }

    public int getGold(){
        return aGold;
    }

    public void addCard(ResourceCard pResourceCard){
        aResourceCards.get(pResourceCard.getType()).add(pResourceCard);
    }

    public void addCard(CommodityCard pCommodityCard){
        aCommodityCards.get(pCommodityCard.getType()).add(pCommodityCard);
    }

    public ResourceCard removeResource(ResourceCard.ResourceType pResourceType){
        return aResourceCards.get(pResourceType).remove();
    }

    public void addGold(){
        aGold += 2;
    }

    public void addSettlement(Settlement pSettlement){
        aSettlements.add(pSettlement);
    }

    public void setRoads(Queue<Road> pRoads){
        aRoads = pRoads;
    }

    public void setCities(Queue<City> pCities){
        aCities = pCities;
    }

    public void setSettlements(Queue<Settlement> pSettlements){
        aSettlements = pSettlements;
    }

    public void setShips(Queue<Ship> pShips){
        aShips = pShips;
    }

    public void setWalls(Queue<Wall> pWalls){
        aWalls = pWalls;
    }

    public void setBasicKnights(Queue<BasicKnight> pBasicKnights){
        aBasicKnights = pBasicKnights;
    }

    public void setStrongKnights(Queue<StrongKnight> pStrongKnights){
        aStrongKnights = pStrongKnights;
    }

    public void setMightyKnights(Queue<MightyKnight> pMightyKnights){
        aMightyKnights = pMightyKnights;
    }

    public boolean hasMerchant(){return aMerchant;}

    public boolean hasLongestTradeRoute(){return aLongestTradeRoute;}

    public boolean hasAqueduct(){return aAqueduct;}

    public boolean hasFortress(){return aFortress;}

    public boolean hasTradingHouse(){return aTradingHouse;}

    public boolean canGetWall(){
        return !aWalls.isEmpty();
    }

    public boolean canGetRoad(){
        return !aRoads.isEmpty();
    }

    public boolean canGetCity(){
        return !aCities.isEmpty();
    }

    public boolean canGetShip(){
        return !aShips.isEmpty();
    }

    public boolean canGetSettlement(){
        return !aSettlements.isEmpty();
    }

    public boolean canGetBasicKnight(){
        return !aBasicKnights.isEmpty();
    }

    public boolean canGetStrongKnight(){
        return !aStrongKnights.isEmpty();
    }

    public boolean canGetMightyKnight(){
        return !aMightyKnights.isEmpty();
    }

    public Road giveRoad(){
        return aRoads.remove();
    }

    public Settlement giveSettlement(){
        return aSettlements.remove();
    }

    public City giveCity(){
        return aCities.remove();
    }

    public Ship giveShip(){
        return aShips.remove();
    }

    public BasicKnight giveBasicKnight(){
        return aBasicKnights.remove();
    }

    public StrongKnight giveStrongKnight(){
        return aStrongKnights.remove();
    }

    public MightyKnight giveMightyKnight(){
        return aMightyKnights.remove();
    }

    public HashMap<ResourceCard.ResourceType, Queue<ResourceCard>> getResourceCards(){
        return aResourceCards;
    }

    public HashMap<CommodityCard.CommodityType, Queue<CommodityCard>> getCommodityCards(){
        return aCommodityCards;
    }
}