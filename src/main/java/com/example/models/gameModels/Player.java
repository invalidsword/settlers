package com.example.models.gameModels;
import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameid;

    @Override
    public String toString() {
        return "Player{" +
                ", aColor='" + aColor + '\'' +
                ", aUsername='" + aUsername + '\'' +
                '}';
    }

    private final String aColor;
    private final String aUsername;
    private final int aIndex;
    private int aVPs;
    private int aGold;
    private int aRouteLength;
    private int aProgressCardAmount;
    private int aArmySize;
    private int aPoliticsLevel;
    private int aTradeLevel;
    private int aScienceLevel;
    private boolean aMerchant;
    private boolean aLargestArmy;
    private boolean aLongestTradeRoute;
    private boolean aAqueduct;
    private boolean aFortress;
    private boolean aTradingHouse;

    @ElementCollection
    private HashMap<StealableCard.Resource, Integer> aMaritimeTradeRates = new HashMap<>();
    @ElementCollection
    private Map<StealableCard.Resource, Integer> aResourceCards = new HashMap<>();
    @ElementCollection
    private Map<StealableCard.Commodity, Integer> aCommodityCards = new HashMap<>();
    @ElementCollection
    private Map<ProgressCard.Trade, Integer> aTradeCards= new HashMap<>();
    @ElementCollection
    private Map<ProgressCard.Politics, Integer> aPoliticsCards= new HashMap<>();
    @ElementCollection
    private Map<ProgressCard.Science, Integer> aScienceCards= new HashMap<>();
    @ElementCollection
    private Map<Unit.Building, Integer> aBuildings = new HashMap<>();
    @ElementCollection
    private Map<Unit.Knight, Integer> aKnights = new HashMap<>();
    @ElementCollection
    private Map<Unit.Transport, Integer> aTransports = new HashMap<>();


    protected Player(){
        aUsername = null;
        aColor = null;
        aIndex = 0;
    }

    public Player(String pUsername, String pColor, int pIndex) {
        aUsername = pUsername;
        aColor = pColor;
        aIndex = pIndex;
        aVPs = 0;
        aGold = 0;
        aRouteLength = 0;
        aArmySize = 0;
        aProgressCardAmount = 0;
        aPoliticsLevel = 0;
        aTradeLevel = 0;
        aScienceLevel = 0;
        aMerchant = false;
        aLargestArmy = false;
        aLongestTradeRoute = false;
        aAqueduct = false;
        aFortress = false;
        aTradingHouse = false;

        for (StealableCard.Resource resource : StealableCard.Resource.values()) {
            aMaritimeTradeRates.put(resource, 4);
        }
        for (StealableCard.Resource resource : StealableCard.Resource.values()) {
            aResourceCards.put(resource, 0);
        }
        for (StealableCard.Commodity commodity : StealableCard.Commodity.values()) {
            aCommodityCards.put(commodity, 0);
        }
        for (Unit.Building building : Unit.Building.values()) {
            aBuildings.put(building, Unit.Building.maxBuildings());
        }
        for (Unit.Knight knight : Unit.Knight.values()) {
            aKnights.put(knight, Unit.Knight.maxKnights());
        }
        for (Unit.Transport transport : Unit.Transport.values()) {
            aTransports.put(transport, Unit.Transport.maxTransports());
        }
    }

    public void setaResourceCards(Map<StealableCard.Resource, Integer> aResourceCards) {
        this.aResourceCards = aResourceCards;
    }

    public void setaCommodityCards(Map<StealableCard.Commodity, Integer> aCommodityCards) {
        this.aCommodityCards = aCommodityCards;
    }

    public void setaTradeCards(Map<ProgressCard.Trade, Integer> aTradeCards) {
        this.aTradeCards = aTradeCards;
    }

    public void setaPoliticsCards(Map<ProgressCard.Politics, Integer> aPoliticsCards) {
        this.aPoliticsCards = aPoliticsCards;
    }

    public void setaScienceCards(Map<ProgressCard.Science, Integer> aScienceCards) {
        this.aScienceCards = aScienceCards;
    }

    public void setaBuildings(Map<Unit.Building, Integer> aBuildings) {
        this.aBuildings = aBuildings;
    }

    public void setaKnights(Map<Unit.Knight, Integer> aKnights) {
        this.aKnights = aKnights;
    }

    public void setaTransports(Map<Unit.Transport, Integer> aTransports) {
        this.aTransports = aTransports;
    }

    public int getaArmySize() {
        return aArmySize;
    }

    public void setaArmySize(int aArmySize) {
        this.aArmySize = aArmySize;
    }

    public int getaPoliticsLevel() {
        return aPoliticsLevel;
    }

    public void setaPoliticsLevel(int aPoliticsLevel) {
        this.aPoliticsLevel = aPoliticsLevel;
    }

    public int getaTradeLevel() {
        return aTradeLevel;
    }

    public void setaTradeLevel(int aTradeLevel) {
        this.aTradeLevel = aTradeLevel;
    }

    public int getaScienceLevel() {
        return aScienceLevel;
    }

    public void setaScienceLevel(int aScienceLevel) {
        this.aScienceLevel = aScienceLevel;
    }

    public boolean isaLargestArmy() {
        return aLargestArmy;
    }

    public void setaLargestArmy(boolean aLargestArmy) {
        this.aLargestArmy = aLargestArmy;
    }

    public String getaColor() {
        return aColor;
    }

    public String getaUsername() {
        return aUsername;
    }

    public int getaIndex() {
        return aIndex;
    }

    public int getaVPs() {
        return aVPs;
    }

    public void setaVPs(int aVPs) {
        this.aVPs = aVPs;
    }

    public int getaGold() {
        return aGold;
    }

    public void setaGold(int aGold) {
        this.aGold = aGold;
    }

    public int getaRouteLength() {
        return aRouteLength;
    }

    public void setaRouteLength(int aRouteLength) {
        this.aRouteLength = aRouteLength;
    }

    public int getaProgressCardAmount() {
        return aProgressCardAmount;
    }

    public void setaProgressCardAmount(int aProgressCardAmount) {
        this.aProgressCardAmount = aProgressCardAmount;
    }

    public boolean isaMerchant() {
        return aMerchant;
    }

    public void setaMerchant(boolean aMerchant) {
        this.aMerchant = aMerchant;
    }

    public boolean isaLongestTradeRoute() {
        return aLongestTradeRoute;
    }

    public void setaLongestTradeRoute(boolean aLongestTradeRoute) {
        this.aLongestTradeRoute = aLongestTradeRoute;
    }

    public boolean isaAqueduct() {
        return aAqueduct;
    }

    public void setaAqueduct(boolean aAqueduct) {
        this.aAqueduct = aAqueduct;
    }

    public boolean isaFortress() {
        return aFortress;
    }

    public void setaFortress(boolean aFortress) {
        this.aFortress = aFortress;
    }

    public boolean isaTradingHouse() {
        return aTradingHouse;
    }

    public void setaTradingHouse(boolean aTradingHouse) {
        this.aTradingHouse = aTradingHouse;
    }

    public Map<StealableCard.Resource, Integer> getaResourceCards() {
        return aResourceCards;
    }

    public void setaResourceCards(HashMap<StealableCard.Resource, Integer> aResourceCards) {
        this.aResourceCards = aResourceCards;
    }

    public Map<StealableCard.Commodity, Integer> getaCommodityCards() {
        return aCommodityCards;
    }

    public void setaCommodityCards(HashMap<StealableCard.Commodity, Integer> aCommodityCards) {
        this.aCommodityCards = aCommodityCards;
    }

    public Map<ProgressCard.Trade, Integer> getaTradeCards() {
        return aTradeCards;
    }

    public void setaTradeCards(HashMap<ProgressCard.Trade, Integer> aTradeCards) {
        this.aTradeCards = aTradeCards;
    }

    public Map<ProgressCard.Politics, Integer> getaPoliticsCards() {
        return aPoliticsCards;
    }

    public void setaPoliticsCards(HashMap<ProgressCard.Politics, Integer> aPoliticsCards) {
        this.aPoliticsCards = aPoliticsCards;
    }

    public Map<ProgressCard.Science, Integer> getaScienceCards() {
        return aScienceCards;
    }

    public void setaScienceCards(HashMap<ProgressCard.Science, Integer> aScienceCards) {
        this.aScienceCards = aScienceCards;
    }

    public Map<Unit.Building, Integer> getaBuildings() {
        return aBuildings;
    }

    public void setaBuildings(HashMap<Unit.Building, Integer> aBuildings) {
        this.aBuildings = aBuildings;
    }

    public Map<Unit.Knight, Integer> getaKnights() {
        return aKnights;
    }

    public void setaKnights(HashMap<Unit.Knight, Integer> aKnights) {
        this.aKnights = aKnights;
    }

    public Map<Unit.Transport, Integer> getaTransports() {
        return aTransports;
    }

    public void setaTransports(HashMap<Unit.Transport, Integer> aTransports) {
        this.aTransports = aTransports;
    }

    public HashMap<StealableCard.Resource, Integer> getaMaritimeTradeRates() {
        return aMaritimeTradeRates;
    }

    public void setaMaritimeTradeRates(HashMap<StealableCard.Resource, Integer> aMaritimeTradeRates) {
        this.aMaritimeTradeRates = aMaritimeTradeRates;
    }

    //increase commodity upgrade levels
    public void upgradePolitics(){
        aPoliticsLevel++;
    }
    public void upgradeTrade(){
        aTradeLevel++;
    }
    public void upgradeScience(){
        aScienceLevel++;
    }
    public boolean upgradePoliticsEligibility(){
        return (aPoliticsLevel < 5);
    }
    public boolean upgradeTradeEligibility(){
        return (aTradeLevel < 5);
    }
    public boolean upgradeScienceEligiblity(){
        return (aScienceLevel < 5);
    }

    //add and remove gold
    public void addGold() {
        aGold += 2;
    }
    public void removeGold() {
        assert (aGold >= 2);
        aGold -= 2;
    }

    //add and remove resources
    public void addResource(StealableCard.Resource pResource, int pAmount) {
        aResourceCards.put(pResource, aResourceCards.get(pResource) + pAmount);
    }
    public void removeResource(StealableCard.Resource pResource, int pAmount) {
        assert (aResourceCards.get(pResource) >= pAmount);
        aResourceCards.put(pResource, aResourceCards.get(pResource) - pAmount);
    }

    //add and remove commodities
    public void addCommodity(StealableCard.Commodity pCommodity, int pAmount) {
        aCommodityCards.put(pCommodity, aCommodityCards.get(pCommodity) + pAmount);
    }
    public void removeCommodity(StealableCard.Commodity pCommodity, int pAmount) {
        assert (aCommodityCards.get(pCommodity) >= pAmount);
        aCommodityCards.put(pCommodity, aCommodityCards.get(pCommodity) - pAmount);
    }

    //add and remove trade progress cards
    public void addTradeCard(ProgressCard.Trade pTradeCard) {
        if (aTradeCards.containsKey(pTradeCard)) {
            aTradeCards.put(pTradeCard, aTradeCards.get(pTradeCard) + 1);
        } else {
            aTradeCards.put(pTradeCard, 1);
        }
        aProgressCardAmount++;
    }
    public void removeTradeCard(ProgressCard.Trade pTradeCard){
        aTradeCards.put(pTradeCard, aTradeCards.get(pTradeCard) - 1);
        aProgressCardAmount--;
    }

    //add and remove politics progress cards
    public void addPoliticsCard(ProgressCard.Politics pPoliticsCard) {
        if (aPoliticsCards.containsKey(pPoliticsCard)) {
            aPoliticsCards.put(pPoliticsCard, aPoliticsCards.get(pPoliticsCard) + 1);
        } else {
            aPoliticsCards.put(pPoliticsCard, 1);
        }
        aProgressCardAmount++;
    }
    public void removePoliticsCard(ProgressCard.Politics pPoliticsCard){
        aPoliticsCards.put(pPoliticsCard, aPoliticsCards.get(pPoliticsCard) - 1);
        aProgressCardAmount--;
    }
   
    //add and remove trade science cards
    public void addScienceCard(ProgressCard.Science pScienceCard) {
        if (aScienceCards.containsKey(pScienceCard)) {
            aScienceCards.put(pScienceCard, aScienceCards.get(pScienceCard) + 1);
        } else {
            aScienceCards.put(pScienceCard, 1);
        }
        aProgressCardAmount++;
    }
    public void removeScienceCard(ProgressCard.Science pScienceCard){
        aScienceCards.put(pScienceCard, aScienceCards.get(pScienceCard) - 1);
        aProgressCardAmount--;
    }
    //add and remove buildings
    public void addBuilding(Unit.Building pBuilding) {
        assert (aBuildings.get(pBuilding) < Unit.Building.maxBuildings());
        aBuildings.put(pBuilding, aBuildings.get(pBuilding) + 1);
    }
    private boolean canGetBuilding(Unit.Building pBuilding) {
        return (aBuildings.get(pBuilding) > 0);
    }
    public OwnedBuilding removeBuilding(Unit.Building pBuilding) {
        assert (canGetBuilding(pBuilding));
        aBuildings.put(pBuilding, aBuildings.get(pBuilding) - 1);
        return new OwnedBuilding(this, pBuilding);
    }

    //add and remove knights
    public void addKnight(Unit.Knight pKnight) {
        assert (aKnights.get(pKnight) < Unit.Knight.maxKnights());
        aKnights.put(pKnight, aKnights.get(pKnight) + 1);
    }
    public boolean canGetKnight(Unit.Knight pKnight) {
        return (aKnights.get(pKnight) > 0);
    }
    public OwnedKnight removeKnight(Unit.Knight pKnight) {
        assert (canGetKnight(pKnight));
        aKnights.put(pKnight, aKnights.get(pKnight) - 1);
        return new OwnedKnight(this, pKnight);
    }

    //add and remove transports
    public void addTransport(Unit.Transport pTransport) {
        assert (aTransports.get(pTransport) < Unit.Transport.maxTransports());
        aTransports.put(pTransport, aTransports.get(pTransport) + 1);
    }
    private boolean canGetTransport(Unit.Transport pTransport) {
        return (aTransports.get(pTransport) > 0);
    }
    public OwnedTransport removeTransport(Unit.Transport pTransport) {
        assert (canGetTransport(pTransport));
        aTransports.put(pTransport, aTransports.get(pTransport) - 1);
        return new OwnedTransport(this, pTransport);
    }
}
