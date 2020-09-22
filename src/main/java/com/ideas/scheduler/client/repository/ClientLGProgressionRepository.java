package com.ideas.scheduler.client.repository;

import com.ideas.scheduler.client.service.ClientLGProgression;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientLGProgressionRepository extends CrudRepository<ClientLGProgression, Integer> {
    List<ClientLGProgression> findAllByClientId(int clientId);
    List<ClientLGProgression> findAllByClientIdAndLearningGroupId(int clientId, int learningGroupId);
    ClientLGProgression findByClientIdAndLearningSessionId(int clientId, int learningSessionId);
}
