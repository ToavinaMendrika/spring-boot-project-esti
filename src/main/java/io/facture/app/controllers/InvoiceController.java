package io.facture.app.controllers;

import io.facture.app.entities.Client;
import io.facture.app.entities.Invoice;
import io.facture.app.entities.User;
import io.facture.app.services.ClientService;
import io.facture.app.services.InvoiceService;
import io.facture.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;


    @GetMapping("/admin/invoice/add")
    public String createIndex(Client client, Invoice invoice, Authentication authentication, Model model){
        User user = userService.getCurrentUser(authentication);
        List<Client> clients = clientService.getUserClient(user);
        model.addAttribute("clients", clients);
        model.addAttribute("user", user);
        model.addAttribute("now", new Date());
        return "admin/invoice/create";
    }

    @PostMapping(value = "/admin/invoice/add", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String create(@Valid Invoice invoice, Client client, BindingResult bindingResult, Authentication authentication, Model model)
    {
        User user = userService.getCurrentUser(authentication);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS");
        String dateString = format.format( new Date());
        invoice.setIdentifier("invoice-"+user.getName()+"-"+dateString);
        if(bindingResult.hasErrors()){
            List<Client> clients = clientService.getUserClient(user);
            model.addAttribute("clients", clients);
            model.addAttribute("user", user);
            model.addAttribute("now", new Date());
            return "admin/invoice/create";
        }
        Invoice invoiceSaved = invoiceService.saveInvoice(invoice);
        return "redirect:/admin/invoice/"+ invoiceSaved.getId();
    }

    @GetMapping(value = "/admin/invoice/{id}/delete")
    public String delete(@PathVariable(value = "id") String id, Authentication authentication)
    {
        User user = userService.getCurrentUser(authentication);
        Long invoiceId = Long.parseLong(id);
        Invoice invoice = invoiceService.findInvoice(invoiceId);
        if(invoice != null){
            if(invoiceService.isOwnInvoice(invoice, user)){
                invoiceService.deleteInvoice(invoice);
            }
        }
        else{
            return "redirect:/404";
        }

        return "redirect:/admin/home";
    }

    @GetMapping(value = "/admin/invoice/{id}")
    public String show(@PathVariable(value = "id") String id, Authentication authentication, Model model)
    {
        User user = userService.getCurrentUser(authentication);
        Long invoiceId = Long.parseLong(id);
        Invoice invoice = invoiceService.findInvoice(invoiceId);
        if(invoice != null){
            if(invoiceService.isOwnInvoice(invoice, user)){
                model.addAttribute("invoice", invoice);
                model.addAttribute("user", user);
                return "admin/invoice/show";
            }
        }
        return "redirect:/404";
    }
}
