package io.facture.app.services;

import io.facture.app.entities.Client;
import io.facture.app.entities.Invoice;
import io.facture.app.entities.User;
import io.facture.app.repositories.ClientRepository;
import io.facture.app.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class InvoiceService {

    private InvoiceRepository invoiceRepository;

    private ClientRepository clientRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository, ClientRepository clientRepository)
    {
        this.invoiceRepository = invoiceRepository;
        this.clientRepository = clientRepository;
    }

    public Invoice saveInvoice(Invoice invoice)
    {
        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getAllUserInvoice(User user)
    {
        List<Client> clients = clientRepository.findByUsers(user);
        List<Invoice> invoices = invoiceRepository.findInvoiceByClientIn(clients);
        return invoices;
    }

    @Transactional
    public Invoice deleteInvoice(Invoice invoice)
    {
        invoiceRepository.delete(invoice);
        return invoice;
    }

    public Invoice findInvoice(long id)
    {
        return  invoiceRepository.findById(id);
    }

    public boolean isOwnInvoice(Invoice invoice, User user)
    {
        return invoice.getClient().getUsers().contains(user);
    }

    public long countInvoice(User user)
    {
        return  invoiceRepository.countInvoiceByUser(user.getId());
    }

    public List<Invoice> getAllInvoiceInOneMounth(User user)
    {
        OffsetDateTime endDate = OffsetDateTime.now();
        OffsetDateTime startDate = OffsetDateTime.now().minusDays(30);

        return invoiceRepository.findInvoiceByUserInDate(startDate, endDate, user.getId());
    }
}
