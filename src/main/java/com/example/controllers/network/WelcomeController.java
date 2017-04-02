package com.example.controllers.network;

import com.example.forms.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.repositories.UserRepository;


@Controller
public class WelcomeController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String welcome(Login login, Model model) {

        return "home";
    }



}