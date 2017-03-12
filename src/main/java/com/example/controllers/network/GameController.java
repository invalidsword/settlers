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
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private static List<String> currPlayerList = new ArrayList<String>(); // Get a list of players too?
    private static Game aGame;
    private static Map<String, Player> aPlayers ; // here

    private static int placeSettlementAndRoadCounter = 0;
    private static int placeCityAndRoadCounter = 0;

    private static int currPlayerTurn = 0;
    private static int currNumPlayers;


    public static void setCurrPlayerList(ArrayList<String> pList){
        for (String s : pList){
            currPlayerList.add(s);
        }

        currNumPlayers = currPlayerList.size();
    }

    public static void createGame(){
        aGame = new Game(10,currPlayerList,3 );
    }


    @RequestMapping(value="/game", method= RequestMethod.GET)
    public String game(ModelMap model, Principal principal ) {

        String name = principal.getName(); //get logged in username
        model.addAttribute("username", name);
        model.addAttribute("startingPlayer",currPlayerList.get(0));


        return "game";
    }


    @MessageMapping("/placepiece")
    @SendTo("/topic/piece")
    public ViewPeice placepiece(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/city")
    public ViewPeice placecity(ViewPeice pNew){

        return pNew;

    }

    @MessageMapping("/rolldice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){


        //backend code goes here Payout

        return pDice;
    }

    @MessageMapping("/endturn")
    @SendTo("/topic/turninfo")
    public String endTurn(Principal user){

        System.out.println("I am here!!!!!!!!!!!!!");
        currPlayerTurn = (currPlayerTurn + 1)%currNumPlayers;
        //backend code goes here


        System.out.println("Returning!!!!!!!!");
        return currPlayerList.get(currPlayerTurn);
    }


    @MessageMapping("/edge")
    public void getEdge(ViewEdge pEdge) throws Exception{
        Edge aEdge = new Edge(pEdge.getId());
    aGame.getEdges().put(aEdge.getId(),aEdge);
    aGame.lEdges.add(aEdge);
    }

    @MessageMapping("/hex")
    public void getHex(ViewHex pHex) throws Exception{

        Hex aHex;

        switch (pHex.getTerrainType())
        {
            case "wood" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Forest ); break;
            case "ore" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Mountains); break;
            case "brick" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Hills); break;
            case "sheep" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Pasture ); break;
            case "gold" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.GoldMine); break;
            case "wheat" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Fields); break;
            case "desert" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Desert); break;
            case "sea" : aHex = new SeaHex(pHex.getId());
            default: aHex= new SeaHex(pHex.getId());
        }
        aGame.getHexes().put(aHex.getId(),aHex);
        aGame.lHexes.add(aHex);
    }

    @MessageMapping("/intersection")
    public void getIntersection(ViewIntersection pIntersection) throws Exception{
        Intersection aIntersection = new Intersection(pIntersection.getId(), HarbourType.None);
        aGame.getIntersections().put(aIntersection.getId(),aIntersection);
        aGame.lIntersections.add(aIntersection);
    }

    @MessageMapping("/setNeighbours")
    public void setNeighbours() throws Exception
    {
       aGame.setAllNeighbours();
    }
}