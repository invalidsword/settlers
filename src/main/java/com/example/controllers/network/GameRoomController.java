package com.example.controllers.network;

import com.example.viewobjects.GameRoomView;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class GameRoomController {



    @RequestMapping(value="/gameroom", method = RequestMethod.GET)
    public String gameroom(Model model) {

        model.addAttribute("playerList", playerList); // display already joined users upon loading the gameroom page
        return "gameroom";
    }

    public ArrayList<String> playerList = new ArrayList<String>();

    @MessageMapping("/ready")
    @SendTo("/topic/gameroom")
    public GameRoomView greeting(Principal principal) throws Exception{


        //Thread.sleep(1000); //simulated delay
        playerList.add(principal.getName());
        System.out.println("adding to list" + principal.getName());

        if(playerList.size() == 3){
            System.out.println("creating list!");
            GameController.setCurrPlayerList(playerList);
            GameController.createGame();
        }

        GameRoomView grv = new GameRoomView(principal.getName(), playerList.size());

        System.out.print(principal.getName());
        //return (playerList);

        return(grv);
    }







}