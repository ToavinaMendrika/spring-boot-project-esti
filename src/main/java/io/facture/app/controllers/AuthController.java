package io.facture.app.controllers;

import io.facture.app.entities.User;
import io.facture.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerIndex(User user)
    {
        return "auth/register";
    }

    @GetMapping("/login")
    public String loginIndex(){
        return "auth/login";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String registerUser(@Valid User user, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors()){
            return "auth/register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
}
