package com.example.controllers.network;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class AuthenticationController {

    @RequestMapping(value="/register", method= RequestMethod.GET)
    public String register(Map<String, Object> model) {

        return "register";
    }



}


