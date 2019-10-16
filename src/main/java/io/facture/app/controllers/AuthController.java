package io.facture.app.controllers;

import io.facture.app.entities.User;
import io.facture.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerIndex(Model model)
    {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/register";
    }

    @GetMapping("/login")
    public String loginIndex(){
        return "auth/login";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public RedirectView registerUser(@ModelAttribute User user)
    {
        userService.saveUser(user);
        return new RedirectView("/login");
    }
}
