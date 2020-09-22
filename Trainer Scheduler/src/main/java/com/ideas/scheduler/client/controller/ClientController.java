package com.ideas.scheduler.client.controller;

import com.ideas.scheduler.client.exception.ClientLGProgressionNotFoundException;
import com.ideas.scheduler.client.exception.ClientNotFoundException;
import com.ideas.scheduler.client.exception.NoDataFoundException;
import com.ideas.scheduler.client.repository.ClientLGProgressionRepository;
import com.ideas.scheduler.client.repository.ClientRepository;
import com.ideas.scheduler.client.service.Client;
import com.ideas.scheduler.client.service.ClientLGProgression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientLGProgressionRepository clientLGProgressionRepository;

    @GetMapping("/clients")
    public List<Client> getClients() {
        List<Client> clients = clientRepository.findAll();
        if (clients.isEmpty()) {
            throw new NoDataFoundException();
        } else {
            return clients;
        }
    }

    @GetMapping("clients/{id}")
    public Client getClientById(@PathVariable("id") int id) {
        Client client = clientRepository.findById(id);
        if (client != null) {
            return client;
        } else {
            throw new ClientNotFoundException(id);
        }
    }

    @GetMapping("clients/{id}/active")
    public boolean isClientActive(@PathVariable("id") int id) {
        Client client = clientRepository.findById(id);
        if (client != null) {
            return client.isActive();
        } else {
            throw new ClientNotFoundException(id);
        }
    }

    @PostMapping("/clients")
    public void addClient(@Validated @RequestBody Client client) {
        clientRepository.save(client);
    }

    @DeleteMapping("/clients/{id}")
    public void hardDeleteClient(@PathVariable("id") int id) {
        clientRepository.deleteById(id).orElseThrow(() -> new ClientNotFoundException(id));
    }

    @PutMapping("/clients/{id}/active")
    public void softDeleteClient(@PathVariable("id") int id) {
        Client clientToDelete = clientRepository.findById(id);
        if (clientToDelete != null) {
            clientToDelete.setInactive();
            clientRepository.save(clientToDelete);
        } else {
            throw new ClientNotFoundException(id);
        }
    }

    /*
    ModifyClient modifies a client based on the creation of another form. If a section corresponding to the Name or Account Code is left empty,
    the function will keep the old values, but if another value is submitted, the object will update the submitted value(s).
     */

    @PutMapping("/clients/{id}")
    public void modifyClient(@PathVariable("id") int id, @RequestBody Client client) {

        Client clientToModify = clientRepository.findById(id);

        if (clientToModify == null) {
            throw new ClientNotFoundException(id);
        } else {
            client.setId(clientToModify.getId());

            if (!StringUtils.isEmpty(client.getName())) {
                clientToModify.setName(client.getName());
            }

            if (!StringUtils.isEmpty(client.getAccountCode())) {
                clientToModify.setAccountCode(client.getAccountCode());
            }

            clientRepository.save(clientToModify);
        }
    }

    // Tracks the progression of a client in a learning group

    @GetMapping("/clients/{id}/lg-progression")
    List<ClientLGProgression> getAllLGProgression(@PathVariable int id) {
        List<ClientLGProgression> clientLGProgressionsList = clientLGProgressionRepository.findAllByClientId(id);
        if (clientLGProgressionsList.isEmpty()) {
            throw new NoDataFoundException();
        } else {
            return clientLGProgressionsList;
        }
    }

    @PostMapping("/clients/{id}/lg-progression")
    void addProgression(@PathVariable int id, @Validated @RequestBody ClientLGProgression lgProgression) {
        clientLGProgressionRepository.save(lgProgression);
    }

    @PutMapping("/clients/{id}/lg-progression/{finished}")
    void changeStatusOfProgression(@PathVariable int id, @PathVariable boolean finished, @RequestParam int learningSessionId) {
        ClientLGProgression lgProgression = clientLGProgressionRepository.findByClientIdAndLearningSessionId(id, learningSessionId);
        if (lgProgression != null) {
            lgProgression.setFinished(finished);
            clientLGProgressionRepository.save(lgProgression);
        } else {
            throw new ClientLGProgressionNotFoundException(id, learningSessionId);
        }
    }

    @GetMapping("/clients/{id}/lg-progression/{learningGroupId)")
    List<ClientLGProgression> getLGProgressionOfClient(@PathVariable int id, @PathVariable int learningGroupId) {
        List<ClientLGProgression> lgProgressions = clientLGProgressionRepository.findAllByClientIdAndLearningGroupId(id, learningGroupId);
        if (lgProgressions.isEmpty()) {
            throw new NoDataFoundException();
        } else {
            return lgProgressions;
        }
    }
}
