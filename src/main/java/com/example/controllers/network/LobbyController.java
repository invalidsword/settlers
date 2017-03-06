package com.example.controllers.network;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class LobbyController {

    @RequestMapping(value="/lobby", method= RequestMethod.GET)
    public String lobby(Map<String, Object> model) {

        return "lobby";
    }

}
