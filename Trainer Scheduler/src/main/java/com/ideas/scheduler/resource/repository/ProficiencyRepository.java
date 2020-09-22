package com.ideas.scheduler.resource.repository;

import com.ideas.scheduler.resource.service.Proficiency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProficiencyRepository extends CrudRepository<Proficiency, Integer> {
    Proficiency findProficiencyById(int id);
    List<Proficiency> findAll();

}
