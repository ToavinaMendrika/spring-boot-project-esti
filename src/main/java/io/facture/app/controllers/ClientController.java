package io.facture.app.controllers;

import io.facture.app.entities.Client;
import io.facture.app.entities.User;
import io.facture.app.services.ClientService;
import io.facture.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    @GetMapping("/admin/invoice/add")
    public String createIndex(Client clientl){
        return "admin/invoice/create";
    }

    @PostMapping(value = "/admin/client/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createAction(@Valid Client client, Authentication authentication){
        User user = userService.getCurrentUser(authentication);
        System.out.println("+++++++++++++++++++++++++++++++");
        System.out.println("Client :" + client.getId());
        System.out.println("+++++++++++++++++++++++++++++++");
        clientService.saveClient(client, user);
        return "redirect:/admin/invoice/add";
    }
}
