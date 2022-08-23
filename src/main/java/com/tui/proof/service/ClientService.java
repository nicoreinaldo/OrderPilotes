package com.tui.proof.service;

import com.tui.proof.model.Client;
import com.tui.proof.model.User;
import com.tui.proof.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Optional;

@Service
@Transactional
public class ClientService {
    @Autowired
    private  ClientRepository clientRepository;

    public Client create(User user, String firstName, String lastName, String telephone) {
        Client newClient = new Client(null,user,firstName,lastName,telephone);
        return save(newClient);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findClient(BigInteger client) {
        return  clientRepository.findClientById(client);
    }

}
