package com.ideas.scheduler.client.repository;

import com.ideas.scheduler.client.service.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {

    List<Client> findAll();
    Client findById(int id);
    Optional<Client> deleteById(int id);
}
