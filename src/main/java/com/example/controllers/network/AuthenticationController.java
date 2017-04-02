package com.example.controllers.network;

import com.example.forms.Register;
import com.example.repositories.User;
import com.example.repositories.UserRepository;
import com.example.repositories.UserRole;
import com.example.repositories.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;



/* Handles all user authentications */
@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;


    @RequestMapping("/")
    public String login() {
        return "lobby";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "home";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String regpage(Register register, Model model)
    {

        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@Valid Register register, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "register";
        }

        String confirm = register.getConfirm();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(register.getPassword());

        User newUser = new User(register.getUsername(),hashedPassword);
        newUser.setEmail(newUser.getUserName());
        userRepository.save(newUser);
        UserRole newRole = new UserRole(newUser, "ROLE_USER");
        userRolesRepository.save(newRole);

        return "redirect:/";
    }



}


