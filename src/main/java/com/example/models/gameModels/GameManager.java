package com.example.models.gameModels;
import com.example.repositories.GameRepository;
import com.example.repositories.UserRepository;
import com.example.viewobjects.ViewProgressCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class GameManager {

    private  Game aGame;

    @Autowired
    private  GameRepository gameRepository;


    public  ArrayList<Player> createPlayers(ArrayList<String> pPlayerNames) {
        ArrayList<String> aPlayerColors = new ArrayList<>();
        ArrayList<String> aPlayerNames = new ArrayList<>();
        ArrayList<Player> aPlayers = new ArrayList<>();

        aPlayerColors.add("red");
        aPlayerColors.add("yellow");
        aPlayerColors.add("green");
        aPlayerColors.add("blue");
        aPlayerNames = pPlayerNames;

        for (int i = 0; i < aPlayerNames.size(); i++) {
            aPlayers.add(new Player(aPlayerNames.get(i), aPlayerColors.get(i), i));
        }
        return aPlayers;
    }

    public void createGame(int pVPsToWin, ArrayList<String> playerNames) {
        List<Player> aPlayers = createPlayers(playerNames);
        Board aBoard = new Board();
        Game game = new Game( pVPsToWin, aPlayers, aBoard);
        aGame = game;
    }

    public void saveGame(){
        System.out.println(aGame);
        gameRepository.save(aGame);
    }


    public Game getGame(){
        return aGame;
    }

    public void rollDice(int pYellow, int pRed, int pEvent){
        aGame.setPhase(Game.GamePhase.TurnFirstPhase);
        aGame.setRedDice(Game.DiceNumber.values()[pRed]);
        aGame.setYellowDice(Game.DiceNumber.values()[pYellow]);
        int eventIndex = pEvent;
        switch (eventIndex){
            case 1:
                aGame.setEventDice(Game.EventType.values()[0]);
            case 2:
                aGame.setEventDice(Game.EventType.values()[0]);;
            case 3:
                aGame.setEventDice(Game.EventType.values()[0]);
            case 4:
                aGame.setEventDice(Game.EventType.values()[1]);
            case 5:
                aGame.setEventDice(Game.EventType.values()[2]);
            case 6:
                aGame.setEventDice(Game.EventType.values()[3]);
        }
        checkBarbarian();
        checkDice();
        aGame.setPhase(Game.GamePhase.TurnDiceRolled);
    }

    private void checkBarbarian(){
        assert (aGame.getTurnCounter() > 2);
        if (aGame.getEventDice() == Game.EventType.Barbarian){
            advanceBarbPosition();
        }
    }

    private void advanceBarbPosition(){
        assert aGame.getBarbarianPosition() > 0;
        aGame.updateBarbarianPosition();
        if (aGame.getBarbarianPosition()== 0){
            barbarianAttack();
            resetBarbPosition();
        }
    }

    private void barbarianAttack(){
        assert (aGame.getBarbarianPosition() == 0);
        calculateStrengths();
        if (aGame.getBarbarianStrength() > aGame.getArmyStrength()){
            defenseLoss();
        }
        defenseVictory();
    }

    private void calculateStrengths(){
        aGame.setBarbarianStrength(0);
        aGame.setArmyStrength(0);
        for (Map.Entry<String, Intersection> intersection : aGame.getBoard().getIntersections().entrySet()){
            if (intersection.getValue().getOccupancyFlag()) {
                if (intersection.getValue().getBuilding() != null){
                    aGame.setBarbarianStrength(aGame.getBarbarianStrength() + 1);
                }
                else if (intersection.getValue().getKnight() != null){
                    if ((intersection.getValue().getKnight().getState())){
                        aGame.setArmyStrength(aGame.getArmyStrength() + (intersection.getValue().getKnight()).getStrength());
                    }
                }
            }
        }
    }

    private void resetBarbPosition(){
        aGame.resetBarbarianPosition();
    }


    private void defenseVictory(){
        //TODO
    }

    private void defenseLoss(){
        //TODO
    }


    public void checkDice() {
        int numberRolled = aGame.getRedDice().add(aGame.getYellowDice());
        int commodityLevel = aGame.getRedDice().toInt();
        Game.EventType commodityType = aGame.getEventDice();
        ArrayList<LandHex> tempLandHexes = aGame.getBoard().getLandHexes().get(numberRolled);
        for (LandHex hex : tempLandHexes) {
            List<Intersection> tempIntersections = hex.getIntersectionNeighbours();
            for (Intersection intersection : tempIntersections) {
                if (intersection.getOccupancyFlag()) {
                    Player owner = getPayee(intersection);
                    boolean isCity = checkIsCity(intersection);
                    payout(owner, hex.getTerrainType(), isCity);
                    progressCardPayout(owner, commodityLevel, commodityType);
                }
            }
        }
    }

    private Player getPayee(Intersection pIntersection){
        assert (pIntersection.getBuilding() != null);
        return pIntersection.getBuilding().getOwner();
    }

    private boolean checkIsCity(Intersection pIntersection){
        return (pIntersection.getBuilding() != null && pIntersection.getBuilding().isCity());
    }

    public void payout(Player pOwner, TerrainType pTerrainType, boolean isCity){
        if (pTerrainType == TerrainType.GoldMine){
            pOwner.addGold();
        }
        else {
            StealableCard.Resource resource = pTerrainType.giveResource();
            pOwner.addResource(resource, 1);
        }

        if (isCity) {
            if (pTerrainType == TerrainType.Hills || pTerrainType == TerrainType.Fields){
                StealableCard.Resource resource = pTerrainType.giveResource();
                pOwner.addResource(resource, 1);
            }
            else if (pTerrainType == TerrainType.GoldMine){
                pOwner.addGold();
            }
            else{
                StealableCard.Commodity commodity = pTerrainType.giveCommodity();
                pOwner.addCommodity(commodity, 1);
            }
        }
    }

    public void progressCardPayout(Player pOwner, int pLevel, Game.EventType pType){
        switch (pType){
            case Barbarian:
                break;
            case Politics:
                if (pOwner.getaPoliticsLevel() >= pLevel){
                    pOwner.addPoliticsCard(ProgressCard.Politics.getRandomPoliticsCard());
                }
            case Trade:
                if (pOwner.getaTradeLevel() >= pLevel){
                    pOwner.addTradeCard(ProgressCard.Trade.getRandomTradeCard());
                }
            case Science:
                if (pOwner.getaScienceLevel() >= pLevel) {
                    pOwner.addScienceCard(ProgressCard.Science.getRandomScienceCard());
                }
        }
    }

    public void setupPayout(){
        for (Map.Entry<String,Intersection> intersection : aGame.getBoard().getIntersections().entrySet()){
            if (intersection.getValue().getBuilding() != null && intersection.getValue().getBuilding().isCity()){
                Player owner = intersection.getValue().getBuilding().getOwner();
                Intersection cityLocation = intersection.getValue();
                for(Hex hex : cityLocation.getHexNeighbours()){
                    TerrainType pTerrainType = hex.getTerrainType();
                    StealableCard.Resource resource = pTerrainType.giveResource();
                    owner.addResource(resource, 1);
                }
            }
        }
    }

    //START FUNCTIONS FOR KNIGHTS
    public void payKnight(Player pPlayer) {
        pPlayer.removeResource(StealableCard.Resource.SHEEP, 1);
        pPlayer.removeResource(StealableCard.Resource.ORE, 1);
    }
    public void placeKnight(Player pPlayer, Intersection pIntersection){
        OwnedKnight knight = pPlayer.removeKnight(Unit.Knight.BASIC);
        pIntersection.setKnight(knight);
    }
    public void upgradeKnight(Player pPlayer, Intersection pIntersection){
        Unit.Knight nextLevel = pIntersection.getKnight().getUpgrade();
        OwnedKnight knight = pPlayer.removeKnight(nextLevel);
    }
    public void activateKnight(Intersection pIntersection){
        pIntersection.getKnight().activate();
    }
    public void payActivation(Player pPlayer){
        pPlayer.removeResource(StealableCard.Resource.WHEAT, 1);
    }
    public boolean checkBuyKnight(Player pPlayer){
        if (pPlayer.getaResourceCards().get(StealableCard.Resource.ORE) == 0 || pPlayer.getaResourceCards().get(StealableCard.Resource.SHEEP) == 0){
            return false;
        }
        return true;
    }
    public boolean checkKnightPlaceEligibility(Intersection pIntersection, String pColor){
        //TODO GEORGE PLS SEND CODE
        return true;
    }
    public boolean checkUpgradeKnightEligibility(Intersection pIntersection, String pColor){
        OwnedKnight knight = pIntersection.getKnight();
        Player owner = knight.getOwner();
        Unit.Knight nextLevel = knight.getUpgrade();
        if (owner.canGetKnight(nextLevel) && owner.getaColor().equals(pColor)){
            return true;
        }
        return false;
    }
    public boolean checkActivateEligibility(Intersection pIntersection, String pColor){
        OwnedKnight knight = pIntersection.getKnight();
        Player owner = knight.getOwner();
        if (owner.getaColor().equals(pColor) && !knight.getState()){
            return true;
        }
        return false;
    }
    //END FUNCTIONS FOR KNIGHTS                                        */

    //FUNCTION COMMON TO SETTLEMENTS AND CITIES
    public boolean checkIntersectionSetupEligibility(Intersection pIntersection){
        if(pIntersection.getOccupancyFlag())
            return false;

        List<Intersection> iNeighbours = pIntersection.getIntersectionNeighbours();
        List<Edge> eNeighbours = pIntersection.getEdgeNeighbours();
        List<Hex> hNeighbours = pIntersection.getHexNeighbours();
        boolean water = true;
        for(Hex aHex:hNeighbours) {
            if(aHex.getTerrainType() != TerrainType.Sea)
                water = false;
        }
        if(water){  // settlement is surrounded by water
            return false;
        }
        for(Intersection aIntersection:iNeighbours) {
            if(aIntersection.getBuilding() != null)
                return false;
        }
        return true;
    }

    //START FUNCTIONS FOR SETTLEMENTS
    public void paySettlement(Player pPlayer) {
        pPlayer.removeResource(StealableCard.Resource.SHEEP, 1);
        pPlayer.removeResource(StealableCard.Resource.WHEAT, 1);
        pPlayer.removeResource(StealableCard.Resource.BRICK, 1);
        pPlayer.removeResource(StealableCard.Resource.WOOD, 1);
    }
    public void placeSettlement(Player pPlayer, Intersection pIntersection){
        OwnedBuilding settlement = pPlayer.removeBuilding(Unit.Building.SETTLEMENT);
        pIntersection.setBuilding(settlement);
    }
    public boolean checkBuySettlement(Player pPlayer) {
        if (pPlayer.getaResourceCards().get(StealableCard.Resource.SHEEP) == 0 ||
                pPlayer.getaResourceCards().get(StealableCard.Resource.ORE) == 0 ||
                pPlayer.getaResourceCards().get(StealableCard.Resource.WOOD) == 0 ||
                pPlayer.getaResourceCards().get(StealableCard.Resource.BRICK) == 0){
            return false;
        }
        return true;
    }
    public boolean checkSettlementPlaceEligibility(Intersection pIntersection, String pColor){
        System.out.println("HELLLLLOOOOOO YOUTUBE");
        if(pIntersection.getOccupancyFlag())
            return false;

        List<Intersection> iNeighbours = pIntersection.getIntersectionNeighbours();
        List<Edge> eNeighbours = pIntersection.getEdgeNeighbours();
        List<Hex> hNeighbours = pIntersection.getHexNeighbours();
        boolean water = true;
        for(Hex aHex:hNeighbours) {
            if(aHex.getTerrainType() != TerrainType.Sea)
                water =false;
        }
        if(water){  // settlement is surrounded by water
            return false;
        }

        for(Intersection aIntersection:iNeighbours) {
            if(aIntersection.getBuilding() != null)
                return  false;
        }

        for(Edge aEdge:eNeighbours){
            if(aEdge.getOccupancyFlag()){
                if(aEdge.getTransport().getOwner().getaColor().equals(pColor))
                    return true;
            }
        }
        return false;
    }
    //END FUNCTIONS FOR SETTLEMENTS

    //START FUNCTIONS FOR CITIES
    public void payCity(Player pPlayer){
        pPlayer.removeResource(StealableCard.Resource.WHEAT, 2);
        pPlayer.removeResource(StealableCard.Resource.ORE, 3);
    }
    public void placeCity(Player pPlayer, Intersection pIntersection){
        OwnedBuilding city = pPlayer.removeBuilding(Unit.Building.CITY);
        pPlayer.addBuilding(Unit.Building.SETTLEMENT);
        pIntersection.setBuilding(city);
    }
    public boolean checkBuyCity(Player pPlayer){
        if (pPlayer.getaResourceCards().get(StealableCard.Resource.ORE) < 3 || pPlayer.getaResourceCards().get(StealableCard.Resource.WHEAT) < 2){
            return false;
        }
        return true;
    }
    public boolean checkCityPlaceEligibility(Intersection pIntersection, String pColor){

        if(pIntersection.getBuilding() != null ){
            if(pIntersection.getBuilding().getOwner().getaColor().equals(pColor) && pIntersection.getBuilding().isCity()==false)
                return true;
        }
        return false;
    }
    //END FUNCTIONS FOR CITIES


    //START FUNCTIONS FOR ROADS
    public void payRoad(Player pPlayer){
        pPlayer.removeResource(StealableCard.Resource.BRICK, 1);
        pPlayer.removeResource(StealableCard.Resource.WOOD, 1);
    }
    public void placeRoad(Player pPlayer, Edge pEdge){
        OwnedTransport road = pPlayer.removeTransport(Unit.Transport.ROAD);
        pEdge.setTransport(road);
    }
    public boolean checkBuyRoad(Player pPlayer){
        if (pPlayer.getaResourceCards().get(StealableCard.Resource.WOOD) == 0 || pPlayer.getaResourceCards().get(StealableCard.Resource.BRICK) == 0){
            return false;
        }
        return true;
    }
    public boolean checkRoadEligibility(Edge pEdge, String pColor){
        if(pEdge.getOccupancyFlag())
            return false;

        List<Edge> Neighbours = pEdge.getEdgeNeighbours();
        List<Hex> hNeighbours = pEdge.getHexNeighbours();
        List<Intersection> iNeighbours = pEdge.getIntersectionNeighbours();
        boolean water = true;
        for(Hex aHex:hNeighbours){
            if(aHex.getTerrainType() != TerrainType.Sea)
                water = false;
        }
        if(water) {
            return false;
        }
        for(Edge aEdge:Neighbours){
            if(aEdge.getOccupancyFlag()) {
                if (aEdge.getTransport().getUnit() == Unit.Transport.ROAD && aEdge.getTransport().getOwner().getaColor() .equals(pColor)) {
                    return true;
                }
            }
        }
        for(Intersection aIntersection:iNeighbours){
            if(aIntersection.getOccupancyFlag()) {
                if (aIntersection.getBuilding()!=null && aIntersection.getBuilding().getOwner().getaColor() .equals(pColor)) {
                    return true;
                }
            }
        }
        return false;
    }
    //END FUNCTIONS FOR ROADS

    public boolean checkShipEligibility(Edge pEdge, String pColor){
        if(pEdge.getOccupancyFlag())
            return false;
        List<Edge> Neighbours = pEdge.getEdgeNeighbours();
        List<Hex> hNeighbours = pEdge.getHexNeighbours();
        List<Intersection> iNeighbours = pEdge.getIntersectionNeighbours();
        boolean water = false;
        for(Hex aHex:hNeighbours){
            if(aHex.getTerrainType() == TerrainType.Sea)
                water = true;
        }
        if(!water) {
            return false;
        }
        for(Edge aEdge:Neighbours){
            if(aEdge.getOccupancyFlag()) {
                if (aEdge.getTransport().getUnit() == Unit.Transport.SHIP && aEdge.getTransport().getOwner().getaColor() .equals(pColor)) {
                    return true;
                }
            }
        }
        for(Intersection aIntersection:iNeighbours){
            if(aIntersection.getOccupancyFlag()) {
                if (aIntersection.getBuilding()!=null && aIntersection.getBuilding().getOwner().getaColor() .equals(pColor)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void placeShip(Player pPlayer, Edge pEdge){
        OwnedTransport ship = pPlayer.removeTransport(Unit.Transport.ROAD);
        pEdge.setTransport(ship);
    }
    public void payShip(Player pPlayer){
        pPlayer.removeResource(StealableCard.Resource.SHEEP, 1);
        pPlayer.removeResource(StealableCard.Resource.WOOD, 1);
    }
    public boolean checkBuyShip(Player pPlayer){
        if (pPlayer.getaResourceCards().get(StealableCard.Resource.WOOD) == 0 || pPlayer.getaResourceCards().get(StealableCard.Resource.SHEEP) == 0){
            return false;
        }
        return true;
    }
    //END FUNCTIONS FOR SHIPS

    public boolean checkPlayerTradeEligibility(PlayerTrade pTrade){
        Player requester = pTrade.getaRequester();
        Player requestee = pTrade.getaRequestee();
        Map<StealableCard.Resource, Integer> requesterResources = requester.getaResourceCards();
        Map<StealableCard.Resource, Integer> requesteeResources = requestee.getaResourceCards();
        Map<StealableCard.Commodity, Integer> requesterCommodities = requester.getaCommodityCards();
        Map<StealableCard.Commodity, Integer> requesteeCommodities = requestee.getaCommodityCards();
        HashMap<StealableCard.Resource, Integer> offeredResources = pTrade.getaOfferedResources();
        HashMap<StealableCard.Resource, Integer> requestedResources = pTrade.getaRequestedResources();
        HashMap<StealableCard.Commodity, Integer> offeredCommodities = pTrade.getaOfferedCommodities();
        HashMap<StealableCard.Commodity, Integer> requestedCommodities = pTrade.getaRequestedCommodities();

        for (StealableCard.Resource resource : StealableCard.Resource.values()){
            if (requesterResources.get(resource) < offeredResources.get(resource) ||
                requesteeResources.get(resource) < requestedResources.get(resource)){
                return false;
            }
        }
        for (StealableCard.Commodity commodity : StealableCard.Commodity.values()){
            if (requesterCommodities.get(commodity) < offeredCommodities.get(commodity) ||
                    requesteeCommodities.get(commodity) < requestedCommodities.get(commodity)){
                return false;
            }

        }
        return true;
    }

    public boolean checkMaritimeTradeEligibility(MaritimeTrade pTrade){
        Player requester = pTrade.getaRequester();
        Map<StealableCard.Resource, Integer> requesterResources = requester.getaResourceCards();
        if (requesterResources.get(pTrade.getaOfferedResource()) < pTrade.getaRequestedAmount()*pTrade.getaTradeRate()){
            return false;
        }
        return true;
    }

    public void endTurn(){
        aGame.setPhase(Game.GamePhase.TurnSecondPhase);
        nextPlayer();
    }

    private void nextPlayer(){
        aGame.updateTurn();
    }

    public Player getPlayerFromString(String pPlayerString){
        Player returnedPlayer = null;
        for (Player player : aGame.getPlayers()){
            if (player.getaUsername().equals(pPlayerString)){
                returnedPlayer = player;
            }
        }
        return returnedPlayer;
    }

    public boolean useTradeCardEligibility(ProgressCard.Trade pTradeCard, Player pPlayer){
        return pPlayer.getaTradeCards().containsKey(pTradeCard) && pPlayer.getaTradeCards().get(pTradeCard) > 0;
    }

    public void useTradeCard(ProgressCard.Trade pTradeCard, Player pPlayer){
        aGame.getExec().executeTradeCard(pTradeCard, pPlayer);
        pPlayer.removeTradeCard(pTradeCard);
    }

    public boolean usePoliticsCardEligibility(ProgressCard.Politics pPoliticsCard, Player pPlayer){
        return pPlayer.getaPoliticsCards().containsKey(pPoliticsCard) && pPlayer.getaPoliticsCards().get(pPoliticsCard) > 0;
    }

    public void usePoliticsCard(ProgressCard.Politics pPoliticsCard, Player pPlayer){
        aGame.getExec().executePoliticsCard(pPoliticsCard, pPlayer);
        pPlayer.removePoliticsCard(pPoliticsCard);
    }

    public boolean useScienceCardEligibility(ProgressCard.Science pScienceCard, Player pPlayer){
        return pPlayer.getaScienceCards().containsKey(pScienceCard) && pPlayer.getaScienceCards().get(pScienceCard) > 0;
    }

    public void useScienceCard(ProgressCard.Science pScienceCard, Player pPlayer){
        aGame.getExec().executeScienceCard(pScienceCard, pPlayer);
        pPlayer.removeScienceCard(pScienceCard);
    }

    public ProgressCard.Politics toPoliticsProgressCard(ViewProgressCard pCard) {
        ProgressCard.Politics aCardName = null;
        for (ProgressCard.Politics cardname : ProgressCard.Politics.values()) {
            if (pCard.getaName().matches(cardname.toString())) {
                aCardName = cardname;
            }
        }
        return aCardName;
    }
    public ProgressCard.Trade toTradeProgressCard(ViewProgressCard pCard) {
        ProgressCard.Trade aCardName = null;
        for (ProgressCard.Trade cardname : ProgressCard.Trade.values()) {
            if (pCard.getaName().matches(cardname.toString())) {
                aCardName = cardname;
            }
        }
        return aCardName;
    }
    public ProgressCard.Science toScienceProgressCard(ViewProgressCard pCard) {
        ProgressCard.Science aCardName = null;
        for (ProgressCard.Science cardname : ProgressCard.Science.values()) {
            if (pCard.getaName().matches(cardname.toString())) {
                aCardName = cardname;
            }
        }
        return aCardName;
    }

    public void buyCityImprovement(Player pPlayer, StealableCard.Commodity pType){
        switch (pType) {
            case COIN:
                pPlayer.upgradePolitics();
            case CLOTH:
                pPlayer.upgradeTrade();
            case PAPER:
                pPlayer.upgradeScience();
        }
    }

    public boolean buyCityImprovementEligibility(Player pPlayer, StealableCard.Commodity pType){
        switch (pType){
            case COIN:
                if (pPlayer.getaCommodityCards().get(StealableCard.Commodity.COIN) < pPlayer.getaPoliticsLevel() + 1 || !pPlayer.upgradePoliticsEligibility()){
                    return false;
                }
                return true;
            case CLOTH:
                if (pPlayer.getaCommodityCards().get(StealableCard.Commodity.CLOTH) < pPlayer.getaTradeLevel() + 1 || !pPlayer.upgradeTradeEligibility()){
                    return false;
                }
                return true;
            case PAPER:
                if (pPlayer.getaCommodityCards().get(StealableCard.Commodity.PAPER) < pPlayer.getaScienceLevel() + 1 || !pPlayer.upgradeScienceEligiblity()){
                    return false;
                }
                return true;
        }
        return false;
    }
}

