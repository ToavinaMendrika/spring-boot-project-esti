package io.facture.app.controllers;

import io.facture.app.entities.Invoice;
import io.facture.app.entities.User;
import io.facture.app.services.ClientService;
import io.facture.app.services.InvoiceService;
import io.facture.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String home(){
        return "pages/home";
    }

    @GetMapping("/admin/home")
    public String adminHome(Authentication authentication, Model model)
    {
        User user = userService.getCurrentUser(authentication);
        Long countClient = clientService.countClient(user);
        Long countInvoice = invoiceService.countInvoice(user);
        List<Invoice> invoices = invoiceService.getAllUserInvoice(user);
        List<Invoice> invoicesOneMouth = invoiceService.getAllInvoiceInOneMounth(user);
        String productsInOneMounth = "";
        long index = 0;
        for (Invoice invoice : invoicesOneMouth){
            if(productsInOneMounth.length()==0){
                productsInOneMounth += "[" + invoice.getProducts() + ",";
            }
            else if(invoicesOneMouth.size()-1 == index){
                productsInOneMounth +=  invoice.getProducts() + "]";
            }
            else{
                productsInOneMounth += invoice.getProducts() + ",";
            }
            index++;
        }
        model.addAttribute("user", user);
        model.addAttribute("invoices", invoices);
        model.addAttribute("countClient", countClient);
        model.addAttribute("countInvoice", countInvoice);
        model.addAttribute("productsInOneMounth", productsInOneMounth);
        return "admin/home";
    }

    @GetMapping("/access-denied")
    public String forbidden()
    {
        return "pages/403";
    }
}
