package com.example.controllers.network;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.Map;

@Controller
public class GameRoomController {

    @RequestMapping(value="/gameroom", method= RequestMethod.GET)
    public String gameroom(Map<String, Object> model) {

        return "gameroom";
    }

    @MessageMapping("/ready")
    @SendTo("/topic/gameroom")
    public String greeting() throws Exception{
        //Thread.sleep(1000); //simulated delay
        return ("YO HAECHAN WHATSS UP");
    }





}