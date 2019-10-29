package io.facture.app.services;

import io.facture.app.entities.Client;
import io.facture.app.entities.User;
import io.facture.app.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class ClientService {

    private ClientRepository clientRepository;
    private EntityManager entityManager;

    @Autowired
    public ClientService(ClientRepository clientRepository, EntityManager entityManager){
        this.clientRepository = clientRepository;
    }

    public void saveClient(Client client, User user)
    {
        client.getUsers().add(user);
        client.setCreated_at(new Date());
        clientRepository.saveAndFlush(client);
    }

    public List<Client> getUserClient(User user)
    {
        List<Client> clients = new ArrayList<>();
        clients = clientRepository.findByUsers(user);
        return clients;
    }
}
