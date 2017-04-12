package com.example.controllers.network;

import com.example.models.gameModels.Game;
import com.example.models.gameModels.GameManager;
import com.example.viewobjects.GameRoomView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class GameRoomController {

    @Autowired
    private GameManager gameManager;


    public static ArrayList<String> playerList = new ArrayList<String>();
    public static ArrayList<Game> savedGames = new ArrayList<Game>();
    public static ArrayList<String> savedGameNames = new ArrayList<>();



    @RequestMapping(value="/gameroom", method = RequestMethod.GET)
    public String gameroom(Model model) {


        savedGameNames = new ArrayList<>();

        for(Game game : savedGames){
            savedGameNames.add(game.getGameName());
        }

        savedGameNames.add("DummyGame1");
        savedGameNames.add("DummyGame2");
        savedGameNames.add("DummyGame3");


        model.addAttribute("savedGameNames", savedGameNames);
        model.addAttribute("playerList", playerList); // display already joined users upon loading the gameroom page
        return "gameroom";
    }


    public static void resetGameRoomPlayerList(){ playerList.clear();}

    @MessageMapping("/ready")
    @SendTo("/topic/gameroom")
    public GameRoomView ready(Principal principal) throws Exception{


        playerList.add(principal.getName());
        System.out.println("adding to list" + principal.getName());


        if(playerList.size() == 3){
            System.out.println("creating list!");
            GameController.setCurrPlayerList(playerList);
            gameManager.createGame(10, playerList);
            gameManager.saveGame();
        }

        GameRoomView grv = new GameRoomView(principal.getName(), playerList.size());

        System.out.print(principal.getName());
        //return (playerList);

        return(grv);
    }

    @RequestMapping(value="/loadgame", method = RequestMethod.POST)
    public String loadGame(@RequestBody String id){
        System.out.println("Load game id: "+ id);
        return "gameroom";
    }







}