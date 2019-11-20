package io.facture.app.repositories;

import io.facture.app.entities.Client;
import io.facture.app.entities.Invoice;
import io.facture.app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.OffsetDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>  {
    List<Invoice> findInvoiceByClientIn(List<Client> clients);
    Invoice findById(long id);
    void delete(Invoice invoice);

    @Query(
            value = "SELECT COUNT(*) FROM invoices i INNER JOIN clients c ON c.id = i.client_id JOIN clients_users cu ON cu.clients_id=c.id JOIN users u ON u.id=cu.users_id WHERE u.id= ?1",
            nativeQuery = true
    )
    long countInvoiceByUser(Long userID);

    @Query(
            value = "SELECT i.id, i.created_at, i.identifier, i.products, i.client_id FROM invoices i INNER JOIN clients c ON c.id = i.client_id JOIN clients_users cu ON cu.clients_id=c.id JOIN users u ON u.id=cu.users_id WHERE i.created_at BETWEEN ?1 AND ?2 AND u.id=?3",
            nativeQuery = true
    )
    List<Invoice> findInvoiceByUserInDate(OffsetDateTime startDate, OffsetDateTime endDate, Long userId);
}
