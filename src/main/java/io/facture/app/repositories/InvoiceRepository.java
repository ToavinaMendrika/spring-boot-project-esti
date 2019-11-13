package io.facture.app.repositories;

import io.facture.app.entities.Client;
import io.facture.app.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>  {
    List<Invoice> findInvoiceByClientIn(List<Client> clients);
    Invoice findById(long id);
    void delete(Invoice invoice);
}
