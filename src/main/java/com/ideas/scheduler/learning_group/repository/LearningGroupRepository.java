package com.ideas.scheduler.learning_group.repository;

import com.ideas.scheduler.learning_group.service.LearningGroup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LearningGroupRepository extends CrudRepository<LearningGroup, Integer> {
    List<LearningGroup> findAll();
    LearningGroup findById(int id);
    void deleteById(int id);
}
