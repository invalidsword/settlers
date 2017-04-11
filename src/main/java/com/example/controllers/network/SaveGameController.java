package com.example.controllers.network;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class SaveGameController {

    @RequestMapping(value="/savegame", method = RequestMethod.POST)
    public void captureBoardJSON(@RequestBody String payload)
    {

        System.out.println(payload);
    }
}
