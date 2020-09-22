package com.ideas.scheduler.resource.repository;


import com.ideas.scheduler.resource.service.ResourceLanguage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceLanguageRepository extends CrudRepository<ResourceLanguage, Integer> {
    List<ResourceLanguage> findAllByResourceId(int resourceId);
    List<ResourceLanguage> findAll();
    List<ResourceLanguage> findAllByLanguageId(int languageIO);
    void deleteAllByResourceId(int id);
}
