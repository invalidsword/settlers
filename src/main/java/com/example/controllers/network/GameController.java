package com.example.controllers.network;

import com.example.models.gameModels.*;
import com.example.viewobjects.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private static ArrayList<String> currPlayerList = new ArrayList<String>(); // Get a list of players too?
    private static Game aGame;
    private static Map<String, Player> aPlayers ; // here

    private static int placeSettlementAndRoadCounter = 0;
    private static int placeCityAndRoadCounter = 0;

    private static int currPlayerTurn = 0;
    private static int currNumPlayers;

    private static final String player1color = "red";
    private static final String player2color = "yellow";
    private static final String player3color = "green";
    private static final String player4color = "blue";

    private static final PlayerAndPhase pap = new PlayerAndPhase();

    private static int turnCounter = 0;

    public static ArrayList<Player> createPlayers(ArrayList<String> currPlayerList){
        ArrayList<Player> aPlayers = new ArrayList<Player>();
        for (int i = 0; i < currPlayerList.size(); i++){
            if(i == 0) {
                Player player1 = new Player(currPlayerList.get(i), player1color);
                aPlayers.add(player1);
            }
            else if (i == 1) {
                Player player2 = new Player(currPlayerList.get(i), player2color);
                aPlayers.add(player2);
            }
            else if (i == 2) {
                Player player3 = new Player(currPlayerList.get(i), player3color);
                aPlayers.add(player3);
            }
            else if (i == 3) {
                Player player4 = new Player(currPlayerList.get(i), player4color);
                aPlayers.add(player4);
            }
        }
        return aPlayers;
    }

    public static void setCurrPlayerList(ArrayList<String> pList){
        for (String s : pList){
            currPlayerList.add(s);
        }

        currNumPlayers = currPlayerList.size();
        pap.setSetup1(true);
        pap.setSetup2(false);
    }

    public static void createGame(){
        List<String> myColors = new ArrayList<String>();
        myColors.add(0,player1color);
        myColors.add(1,player2color);
        myColors.add(2,player3color);
        myColors.add(3,player3color);
        aGame = new Game(10,createPlayers(currPlayerList),myColors);
        System.out.println(aGame.getPlayers().size());
        aGame.setPlayerProperties(aGame.getPlayers());
    }



    @RequestMapping(value="/game", method= RequestMethod.GET)
    public String game(ModelMap model, Principal caller) {

        String name = caller.getName(); //get logged in username
        model.addAttribute("username", name);
        model.addAttribute("startingPlayer",currPlayerList.get(0));

        for(int i = 0 ; i < currPlayerList.size(); i++){

            if (currPlayerList.get(i).matches(name)){
                String color = "";

                if (i == 0){
                    color = player1color;
                }else if(i == 1){
                    color = player2color;
                }else if(i == 2){
                    color = player3color;
                }else if( i== 3){
                    color = player4color;
                }
                model.addAttribute("myColor", color);
            }

        }

        model.addAttribute("player1",currPlayerList.get(0));
        model.addAttribute("player2",currPlayerList.get(1));
        model.addAttribute("player3",currPlayerList.get(2));
        //model.addAttribute("player4",currPlayerList.get(3));

        model.addAttribute("player1_c", player1color);
        model.addAttribute("player2_c", player2color);
        model.addAttribute("player3_c", player3color);
       // model.addAttribute("player4_c", player4color);

        return "game";
    }



    @MessageMapping("/placesettlement")
    @SendTo("/topic/settlement")
    public ViewPiece placeSettlement(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeSettlement(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/city")
    public ViewPiece placeCity(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeCity(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        return pNew;
    }

    @MessageMapping("/placeroad")
    @SendTo("/topic/road")
    public ViewPiece placeRoad(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeRoad(callingPlayer, aGame.getEdges().get(pNew.getId()));*/
        return pNew;
    }

    @MessageMapping("/setupsettlement")
    @SendTo("/topic/settlement")
    public ViewPiece setupSettlement(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupSettlement(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        return pNew;
    }

    @MessageMapping("/setupcity")
    @SendTo("/topic/city")
    public ViewPiece setupCity(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupCity(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        return pNew;
    }

    @MessageMapping("/setuproad")
    @SendTo("/topic/road")
    public ViewPiece setupRoad(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupRoad(callingPlayer, aGame.getEdges().get(pNew.getId()));
        */
        return pNew;
    }

    @SendTo("/topic/playerIncrement")
    public PlayerIncrement setupPayout(){

        PlayerIncrement increment = new PlayerIncrement();
       // aGame.setupPayout();
        setPlayerIncrement(increment);
        return increment;
    }

    @MessageMapping("/rolldice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){
        /*aGame.rollDice(pDice.getYellow(), pDice.getRed(), pDice.getEvent());*/
        /*diceRollPayout();*/
        return pDice;
    }

    @SendTo("/topic/playerIncrement")
    private PlayerIncrement diceRollPayout(){

        PlayerIncrement increment = new PlayerIncrement();
        //setPlayerIncrement(increment);
        return increment;
    }

    private void setPlayerIncrement(PlayerIncrement pIncrement){
        /*
        for (String pUsername : currPlayerList){
            for (Player player : aGame.getPlayers()) {
                if (pUsername.equals(player.getUsername())) {
                    int index = currPlayerList.indexOf(player.getUsername());
                    switch (index) {
                        case 1:
                            pIncrement.setp1(
                                    player.getGold(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Ore).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Brick).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Wood).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Sheep).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Coin).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Cloth).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Paper).size());
                        case 2:
                            pIncrement.setp2(
                                    player.getGold(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Ore).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Brick).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Wood).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Sheep).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Coin).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Cloth).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Paper).size());
                        case 3:
                            pIncrement.setp3(
                                    player.getGold(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Ore).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Brick).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Wood).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Sheep).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Coin).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Cloth).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Paper).size());
                        case 4:
                            pIncrement.setp4(
                                    player.getGold(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Ore).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Brick).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Wood).size(),
                                    player.getResourceCards().get(ResourceCard.ResourceType.Sheep).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Coin).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Cloth).size(),
                                    player.getCommodityCards().get(CommodityCard.CommodityType.Paper).size());
                    }
                }
            }
        }
        */
    }

    @MessageMapping("/endturn")
    @SendTo("/topic/turninfo")
    public PlayerAndPhase endTurn(Principal user){





        if(turnCounter == (currPlayerList.size()-1)){
            System.out.println("first if");
            System.out.println(currPlayerList.size()-1);
            Collections.reverse(currPlayerList);
            currPlayerTurn = 0;
            pap.setSetup1(false);
            pap.setSetup2(true);

        }else if(turnCounter == (2*(currPlayerList.size())-1)){
            System.out.println("second if");
            System.out.println(currPlayerList.size()-1);
            Collections.reverse(currPlayerList);
            currPlayerTurn = 0;
            pap.setSetup1(false);
            pap.setSetup2(false);

        }else{
            currPlayerTurn = (currPlayerTurn + 1)%currNumPlayers;
        }

        pap.setUsername(currPlayerList.get(currPlayerTurn));

        System.out.println("Player's Turn "+ currPlayerList.get(currPlayerTurn));
        System.out.println("turn count = "+currPlayerTurn);

        this.turnCounter++;

        return pap;
    }


    @MessageMapping("/edge")
    public void getEdge(ViewEdge pEdge) throws Exception{

        Edge aEdge = new Edge(pEdge.getId());
    //    System.out.println(aEdge.getId());
        aGame.getEdges().put(aEdge.getId(),aEdge);
        aGame.lEdges.add(aEdge);

    }

    @MessageMapping("/hex")
    public void getHex(ViewHex pHex) throws Exception{
        // THIS TAKE IN A SINGLE HEX OBJ, NOT A JSON ARRAY
    //    System.out.println(pHex.getId());
        Hex aHex;
        /*
        JSONArray aArray = new JSONArray(bigJson);
        Gson gson = new Gson();

        for(int i=0;i<aArray.length();i++) {

         //   System.out.println(i+" out of "+aArray.length());
            JSONObject jsonHex = aArray.getJSONObject(i);
         //   System.out.println(jsonHex);

           ViewHex pHex = gson.fromJson(jsonHex.toString(), ViewHex.class);
          //  System.out.println(pHex.getId());


            switch (pHex.getTerrainType()) {
                case "wood":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Forest);
                    break;
                case "ore":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Mountains);
                    break;
                case "brick":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Hills);
                    break;
                case "sheep":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Pasture);
                    break;
                case "gold":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.GoldMine);
                    break;
                case "wheat":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Fields);
                    break;
                case "desert":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Desert);
                    break;
                case "sea":
                    aHex = new SeaHex(pHex.getId());
                default:
                    aHex = new SeaHex(pHex.getId());
            }
*/
            aHex = new SeaHex(pHex.getId());
       //     System.out.println(aHex.getId());
            aGame.getHexes().put(aHex.getId(), aHex);
            aGame.lHexes.add(aHex);
       // }

    }

    @MessageMapping("/intersection")
    public void getIntersection(ViewIntersection pIntersection) throws Exception{

        Intersection aIntersection = new Intersection(pIntersection.getId(), HarbourType.None);
  //      System.out.println(pIntersection.getId());
        aGame.getIntersections().put(aIntersection.getId(),aIntersection);
        aGame.lIntersections.add(aIntersection);

    }

    @MessageMapping("/setNeighbours")
    public void setNeighbours() throws Exception
    {
        System.out.println("Setting neighbours");
        aGame.setAllNeighbours();
    }
}