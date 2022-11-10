package com.company.warehouse.controller;

import com.company.warehouse.entity.Client;
import com.company.warehouse.payload.Message;
import com.company.warehouse.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }


    @PostMapping("/add") // this method adds new client in DB
    private Message add(@RequestBody Client client) {
        return clientService.add(client);
    }

    @GetMapping("/get") // this method returns all client data
    private List<Client> getAll() {
        return clientService.getAll();
    }

    @GetMapping("/get/{id}") // this method returns client data by id from DB
    private Client getById(@PathVariable Integer id) {
        return clientService.getById(id);
    }

    @PutMapping("/edite/{id}") //this method edites the client data in DB
    private Message edite(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.edite(id, client);
    }

    @DeleteMapping("/delete/{id}") //this method deletes the client data from DB
    private Message delete(@PathVariable Integer id) {
        return clientService.delete(id);
    }
}
