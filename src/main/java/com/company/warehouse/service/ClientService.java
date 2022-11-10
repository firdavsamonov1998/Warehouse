package com.company.warehouse.service;

import com.company.warehouse.entity.Client;
import com.company.warehouse.payload.Message;
import com.company.warehouse.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    //this mehtod adds new client in DB
    public Message add(Client client) {
        boolean exists = clientRepository.existsByPhone(client.getPhone());
        if (exists) return new Message("This phone is already exist", false);
        Client save = clientRepository.save(client);
        return new Message("Successfully saved", true);
    }


    //this method returns all client
    public List<Client> getAll() {
        return clientRepository.findAll();
    }


    //this method returns the client by id
    public Client getById(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) return new Client();
        return optionalClient.get();
    }


    //this method updates the client data
    public Message edite(Integer id, Client client) {
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);

        Client optionalClient = optional.get();
        optionalClient.setName(client.getName());
        if (client.getPhone() == null) {
            optionalClient.setPhone(optionalClient.getPhone());
        } else {
            optionalClient.setPhone(client.getPhone());
        }

        Client save = clientRepository.save(optionalClient);

        return new Message("Successfully edited", true);
    }

    //this method deletes the client data from DB
    public Message delete(Integer id) {
        Optional<Client> optional = clientRepository.findById(id);
        if (optional.isEmpty()) return new Message("Not founded", false);
        clientRepository.delete(optional.get());
        return new Message("Successfully deleted", true);
    }
}
