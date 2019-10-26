package io.facture.app.services;

import io.facture.app.entities.Client;
import io.facture.app.entities.User;
import io.facture.app.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Service
public class ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public void saveClient(Client client, User user)
    {
        client.getUsers().add(user);
        client.setCreated_at(new Date());
        clientRepository.saveAndFlush(client);
    }
}
