package com.example.models.gameModels;
/**
 * Created by G on 17/03/03.
 */

public class GameManager {
//    private GameManager gameManager = new GameManager();
//
//    // MINGS GRAVEYARD
//
//
//    private Game aGame;
//    int aCount;
//
//    private GameManager(){
//        aCount = 0;
//    }
//
//
//    public void createGame(int pVPsToWin, ArrayList<String> playerNames){
//
//        HashMap<Integer, ArrayList<LandHex>> pLandHexes = new HashMap<Integer, ArrayList<LandHex>>();
//
//        for (int k = 2; k < 13; k++){
//            pLandHexes.put(k, new ArrayList<LandHex>());
//        }
//
//        for (int i = 0; i < 20; i++){
//            for (int j = 0; j < 20; j++){
//                if (pHexes[i][j].getTerrainType() != TerrainType.Sea || pHexes[i][j].getTerrainType() != TerrainType.Desert || pHexes[i][j].getTerrainType() != null){
//                     pLandHexes.get(((LandHex)pHexes[i][j]).getProductionNumber()).add((LandHex)pHexes[i][j]);
//                }
//            }
//        }
//
//        HashMap<String, Player> aListofPlayers = createPlayers(playerNames);
//        GameManager.setPlayerProperties(aListofPlayers);
//        Game newGame = new Game(pVPsToWin, aListofPlayers, aCount++);
//        aGame = newGame;
//    }
//
//    public static HashMap<String, Player> createPlayers(ArrayList<String> pUsernames){
//        HashMap<String, Player> aPlayers = new HashMap<String,Player>();
//        if (pUsernames.get(0) != null) {
//            Player p1 = new Player(pUsernames.get(0), Player.Color.Blue);
//            aPlayers.put(p1.getUsername(), p1);
//        }
//        if (pUsernames.get(1) != null) {
//            Player p2 = new Player(pUsernames.get(1), Player.Color.Orange);
//            aPlayers.put(p2.getUsername(), p2);
//        }
//        if (pUsernames.get(2) != null) {
//            Player p3 = new Player(pUsernames.get(2), Player.Color.Red);
//            aPlayers.put(p3.getUsername(), p3);
//        }
//        if (pUsernames.get(3) != null) {
//            Player p4 = new Player(pUsernames.get(3), Player.Color.White);
//            aPlayers.put(p4.getUsername(), p4);
//        }
//        return aPlayers;
//    }
//
//    public static void setPlayerProperties(HashMap<String, Player> pListofPlayers){
//        Queue<City> allCities = City.getUnits();
//        Queue<Settlement> allSettlements = Settlement.getUnits();
//        Queue<Road> allRoads = Road.getUnits();
//        Queue<Ship> allShips = Ship.getUnits();
//        Queue<Wall> allWalls = Wall.getUnits();
//        Queue<BasicKnight> allBasicKnights = BasicKnight.getUnits();
//        Queue<StrongKnight> allStrongKnights = StrongKnight.getUnits();
//        Queue<MightyKnight> allMightyKnights = MightyKnight.getUnits();
//
//        for (Player player : pListofPlayers.values()){
//            Queue<City> aCities = new LinkedList<City>();
//            Queue<Settlement> aSettlements = new LinkedList<Settlement>();
//            Queue<Road> aRoads = new LinkedList<Road>();
//            Queue<Ship> aShips = new LinkedList<Ship>();
//            Queue<Wall> aWalls = new LinkedList<Wall>();
//            Queue<BasicKnight> aBasicKnights = new LinkedList<BasicKnight>();
//            Queue<StrongKnight> aStrongKnights = new LinkedList<StrongKnight>();
//            Queue<MightyKnight> aMightyKnights = new LinkedList<MightyKnight>();
//
//            addCitiesToPlayer(allCities, aCities, player);
//            addSettlementsToPlayer(allSettlements, aSettlements, player);
//            addRoadsToPlayer(allRoads, aRoads, player);
//            addShipsToPlayer(allShips, aShips, player);
//            addWallsToPlayer(allWalls, aWalls, player);
//            addBasicKnightsToPlayer(allBasicKnights, aBasicKnights, player);
//            addStrongKnightsToPlayer(allStrongKnights, aStrongKnights, player);
//            addMightyKnightsToPlayer(allMightyKnights, aMightyKnights, player);
//        }
//    }
//
//    private static void addCitiesToPlayer(Queue<City> allCities, Queue<City> playerCities, Player pPlayer){
//        for (int i = 0; i < 4; i++){
//            playerCities.add(allCities.remove());
//        }
//        pPlayer.setCities(playerCities);
//    }
//
//    private static void addSettlementsToPlayer(Queue<Settlement> allSettlements, Queue<Settlement> playerSettlements, Player pPlayer){
//        for (int i = 0; i < 4; i++){
//            playerSettlements.add(allSettlements.remove());
//        }
//        pPlayer.setSettlements(playerSettlements);
//    }
//
//    private static void addRoadsToPlayer(Queue<Road> allRoads, Queue<Road> playerRoads, Player pPlayer){
//        for (int i = 0; i < 15; i++){
//            playerRoads.add(allRoads.remove());
//        }
//        pPlayer.setRoads(playerRoads);
//    }
//
//    private static void addShipsToPlayer(Queue<Ship> allShips, Queue<Ship> playerShips, Player pPlayer){
//        for (int i = 0; i < 15; i++){
//            playerShips.add(allShips.remove());
//        }
//        pPlayer.setShips(playerShips);
//    }
//
//    private static void addWallsToPlayer(Queue<Wall> allWalls, Queue<Wall> playerWalls, Player pPlayer){
//        for (int i = 0; i < 3; i++){
//            playerWalls.add(allWalls.remove());
//        }
//        pPlayer.setWalls(playerWalls);
//    }
//
//    private static void addBasicKnightsToPlayer(Queue<BasicKnight> allBasicKnights, Queue<BasicKnight> playerBasicKnights, Player pPlayer){
//        for (int i = 0; i < 2; i++){
//            playerBasicKnights.add(allBasicKnights.remove());
//        }
//        pPlayer.setBasicKnights(playerBasicKnights);
//    }
//
//    private static void addStrongKnightsToPlayer(Queue<StrongKnight> allStrongKnights, Queue<StrongKnight> playerStrongKnights, Player pPlayer){
//        for (int i = 0; i < 2; i++){
//            playerStrongKnights.add(allStrongKnights.remove());
//        }
//        pPlayer.setStrongKnights(playerStrongKnights);
//    }
//
//    private static void addMightyKnightsToPlayer(Queue<MightyKnight> allMightyKnights, Queue<MightyKnight> playerMightyKnights, Player pPlayer){
//        for (int i = 0; i < 2; i++){
//            playerMightyKnights.add(allMightyKnights.remove());
//        }
//        pPlayer.setMightyKnights(playerMightyKnights);
//    }
//
//    public static Game getGame(){
//        return aGame;
//        //sql query select game where acount = pcount
//    }
//
//    public static Map<String, Player> getPlayers(){
//        return aGame.getPlayers();
//    }
//
//    public static void rollDice(){
//        assert (aGame != null);
//        aGame.setPhase(Game.GamePhase.TurnFirstPhase);
//        aGame.setRedDice(Game.DiceNumber.values()[(int)(Math.random() * Game.DiceNumber.values().length)]);
//        aGame.setYellowDice(Game.DiceNumber.values()[(int)(Math.random() * Game.DiceNumber.values().length)]);
//        int eventIndex = (int)(Math.random() * 6);
//        switch (eventIndex){
//            case 1:
//                aGame.setEventDice(Game.EventType.values()[0]);
//            case 2:
//                aGame.setEventDice(Game.EventType.values()[0]);;
//            case 3:
//                aGame.setEventDice(Game.EventType.values()[0]);
//            case 4:
//                aGame.setEventDice(Game.EventType.values()[1]);
//            case 5:
//                aGame.setEventDice(Game.EventType.values()[2]);
//            case 6:
//                aGame.setEventDice(Game.EventType.values()[3]);
//        }
//
//        if (aGame.getTurnCounter() > 2) {
//            checkBarbarian();
//        }
//        checkDice();
//        aGame.setPhase(Game.GamePhase.TurnDiceRolled);
//    }
//
//    private static void checkBarbarian(){
//        assert (aGame.getTurnCounter() > 2);
//        if (aGame.getEventDice() == Game.EventType.Barbarian){
//            advanceBarbPosition();
//        }
//    }
//
//    private static void advanceBarbPosition(){
//        assert aGame.getBarbarianPosition() > 0;
//        aGame.updateBarbarianPosition();
//        if (aGame.getBarbarianPosition()== 0){
//            barbarianAttack();
//            resetBarbPosition();
//        }
//    }
//
//    private static void barbarianAttack(){
//        assert (aGame.getBarbarianPosition() == 0);
//        calculateStrengths();
//        if (aGame.getBarbarianStrength() > aGame.getArmyStrength()){
//            defenseLoss();
//        }
//        defenseVictory();
//    }
//
//    private static void calculateStrengths(){
//        aGame.setBarbarianStrength(0);
//        aGame.setArmyStrength(0);
//        for (int i = 0; i < 20; i++){
//            for (int j = 0; j < 20; j++){
//                if (aGame.getIntersections()[i][j].getOccupancyFlag()) {
//                    if (aGame.getIntersections()[i][j].getOccupant().getClass() == City.class) {
//                        aGame.setBarbarianStrength(aGame.getBarbarianStrength() + 1);
//                    }
//                    else if (aGame.getIntersections()[i][j].getOccupant().getClass().getSuperclass() == Knight.class){
//                        if (((Knight)aGame.getIntersections()[i][j].getOccupant()).getState()){
//                            aGame.setArmyStrength(aGame.getArmyStrength() + ((Knight) aGame.getIntersections()[i][j].getOccupant()).getStrength());
//                        }
//                    }
//                }
//            }
//        }
//
//    }
//
//    private static void resetBarbPosition(){
//        aGame.resetBarbarianPosition();
//    }
//
//
//    private static void defenseVictory(){
//        //TODO
//    }
//
//    private static void defenseLoss(){
//        //TODO
//    }
//
//
//    private static void checkDice() {
//        int numberRolled = aGame.getRedDice().add(aGame.getYellowDice());
//        ArrayList<LandHex> tempLandHexes = aGame.getLandHexes().get(numberRolled);
//        for (LandHex hex : tempLandHexes) {
//            Queue<Intersection> tempIntersections = hex.getIntersectionNeighbours();
//            for (Intersection intersection : tempIntersections) {
//                if (intersection.getOccupancyFlag()) {
//                    Player owner = getPayee(intersection);
//                    boolean isCity = checkIsCity(intersection);
//                    payout(owner, hex.getTerrainType(), isCity);
//                }
//            }
//        }
//    }
//
//    private static Player getPayee(Intersection pIntersection){
//        return pIntersection.getOccupant().getOwner();
//    }
//
//    private static boolean checkIsCity(Intersection pIntersection){
//        return (pIntersection.getOccupant().getClass() == City.class);
//    }
//
//    private static void payout(Player pOwner, TerrainType pTerrainType, boolean isCity){
//        if (pTerrainType == TerrainType.GoldMine){
//            if (removeGold()) {
//                pOwner.addGold();
//            }
//        }
//        else {
//            assert(!aGame.getResourceCards().get(pTerrainType.giveResource()).isEmpty());
//            pOwner.addCard(aGame.getResourceCards().get(pTerrainType.giveResource()).remove());
//        }
//
//        if (isCity) {
//            if (pTerrainType == TerrainType.Hills || pTerrainType == TerrainType.Fields){
//                assert(!aGame.getResourceCards().get(pTerrainType.giveResource()).isEmpty());
//                pOwner.addCard(aGame.getResourceCards().get(pTerrainType.giveResource()).remove());
//            }
//            else if (pTerrainType == TerrainType.GoldMine){
//                if (removeGold()){
//                    pOwner.addGold();
//                }
//            }
//            else{
//                assert(!aGame.getCommodityCards().get(pTerrainType.giveCommodity()).isEmpty());
//                pOwner.addCard(aGame.getCommodityCards().get(pTerrainType.giveCommodity()).remove());
//            }
//        }
//    }
//
//    private static boolean removeGold(){
//        if (aGame.getGoldBank() >= 2) {
//            aGame.setGoldBank(aGame.getGoldBank() - 2);
//            return true;
//        }
//        return false;
//    }
//
//    public static void placeSettlement(Player pPlayer, Intersection pIntersection){
//        if (pPlayer != aGame.getCurrentPlayer()){
//            //do nothing
//        }
//        else {
//            assert (!pIntersection.getOccupancyFlag());
//            assert (pPlayer.canGetSettlement());
//            boolean eligible = checkIntersectionEligibility(pIntersection);
//            assert (eligible);
//
//            Settlement settlement = pPlayer.giveSettlement();
//            pIntersection.setOccupant(settlement);
//            if (aGame.getPhase() == Game.GamePhase.SetupRoundOne) {
//                Queue<Hex> neighbours = pIntersection.getHexNeighbours();
//                for (Hex hex : neighbours) {
//                    payout(pPlayer, hex.getTerrainType(), false);
//                }
//            }
//        }
//    }
//
//    public static void placeCity(Player pPlayer, Intersection pIntersection){
//        if (pPlayer != aGame.getCurrentPlayer()){
//            //do nothing
//        }
//        else{
//            assert(!pIntersection.getOccupancyFlag());
//            assert(pPlayer.canGetCity());
//            boolean eligible = checkIntersectionEligibility(pIntersection);
//            assert (eligible);
//
//            City city = pPlayer.giveCity();
//            pIntersection.setOccupant(city);
//            if (aGame.getPhase() == Game.GamePhase.SetupRoundTwo) {
//                Queue<Hex> neighbours = pIntersection.getHexNeighbours();
//                for (Hex hex : neighbours) {
//                    payout(pPlayer, hex.getTerrainType(), true);
//                }
//            }
//        }
//    }
//
//    public static void placeRoad(Player pPlayer, Edge pEdge){
//        if (pPlayer != aGame.getCurrentPlayer()){
//            //do nothing
//        }
//        else{
//            assert(!pEdge.getOccupancyFlag());
//            assert(pPlayer.canGetRoad());
//            assert(checkEdgeEligibility(pEdge));
//
//            Road road = pPlayer.giveRoad();
//            pEdge.setOccupant(road);
//        }
//    }
//
//    private static boolean checkIntersectionEligibility(Intersection pIntersection){
//        boolean eligible = true;
//        Queue<Intersection> neighbourIntersections = pIntersection.getIntersectionNeighbours();
//        for (Intersection intersection: neighbourIntersections){    //Iterate through all neighbours to see if they are occupied
//            if (intersection.getOccupancyFlag()){
//                eligible = false;
//                break;
//            }
//        }
//        if (aGame.getPhase() != Game.GamePhase.SetupRoundOne && aGame.getPhase()!= Game.GamePhase.SetupRoundTwo){
//            eligible = checkNonSetupEligibility(pIntersection);
//        }
//        return eligible;
//    }
//
//    private static boolean checkEdgeEligibility(Edge pEdge){
//        boolean eligible = false;
//        Queue<Intersection> neighbourIntersections = pEdge.getIntersectionNeighbours();
//        for (Intersection intersection: neighbourIntersections){    //Iterate through all neighbours to see if they are occupied by the current player
//            if (intersection.getOccupant().getOwner() != aGame.getCurrentPlayer()){
//                continue;
//            }
//            else{
//                eligible = true;
//                break;
//            }
//        }
//        if (aGame.getPhase()!= Game.GamePhase.SetupRoundOne && aGame.getPhase()!= Game.GamePhase.SetupRoundTwo){
//            eligible = checkNonSetupEligibility(pEdge);
//        }
//        return eligible;
//    }
//
//    private static boolean checkNonSetupEligibility(Geometry pGeometry){
//        Queue<Edge> neighbourEdges = pGeometry.getEdgeNeighbours();
//        boolean eligible = false;
//        for (Edge edge : neighbourEdges){   //if any of the neighbouring edges have a road belonging to the player, it is eligible
//            if (edge.getOccupant().getOwner() != aGame.getCurrentPlayer()){
//                continue;
//            }
//            else{
//                eligible = true;
//                break;
//            }
//        }
//        return eligible;
//
//    }
//
//    public static void endTurn(){
//        aGame.setPhase(Game.GamePhase.TurnSecondPhase);
//        nextPlayer();
//    }
//
//    private static void nextPlayer(){
//        aGame.updateQueue();
//        aGame.updateTurnCounter();
//    }
}

