package io.facture.app.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(){
        return "pages/home";
    }

    @GetMapping("/admin/home")
    public String adminHome()
    {
        return "admin/home";
    }

    @GetMapping(name="/403")
    public String forbidden()
    {
        return "pages/403";
    }
}
