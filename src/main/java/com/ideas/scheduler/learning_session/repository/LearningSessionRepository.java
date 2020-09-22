package com.ideas.scheduler.learning_session.repository;

import com.ideas.scheduler.learning_session.service.LearningSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LearningSessionRepository extends CrudRepository<LearningSession, Integer> {
    List<LearningSession> findAll();
    LearningSession findById(int id);
}
