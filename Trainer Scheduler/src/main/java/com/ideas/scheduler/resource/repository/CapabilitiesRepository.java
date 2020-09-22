package com.ideas.scheduler.resource.repository;

import com.ideas.scheduler.resource.service.Capabilities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CapabilitiesRepository extends CrudRepository<Capabilities, Integer> {
    List<Capabilities> findAll();
    List<Capabilities> findAllByResourceId(int id);
    List<Capabilities> findAllByLearningSessionId(int learningSessionId);
}
