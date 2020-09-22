package com.ideas.scheduler.resource.repository;

import com.ideas.scheduler.resource.service.Languages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanguagesRepository extends CrudRepository<Languages, Integer> {
    List<Languages> findAll();
    Languages findAllById(int id);
    Languages findLanguagesById(int id);
}
