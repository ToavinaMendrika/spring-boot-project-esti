package io.facture.app.controllers;

import io.facture.app.entities.User;
import io.facture.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home(){
        return "pages/home";
    }

    @GetMapping("/admin/home")
    public String adminHome(Authentication authentication, Model model)
    {
        User user = userService.getCurrentUser(authentication);
        model.addAttribute("user", user);
        return "admin/home";
    }

    @GetMapping("/access-denied")
    public String forbidden()
    {
        return "pages/403";
    }
}
