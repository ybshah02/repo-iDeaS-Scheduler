package com.ideas.scheduler.resource.repository;


import com.ideas.scheduler.resource.service.Resource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceRepository extends CrudRepository<Resource, Integer>{

    List<Resource> findAll();
    Resource findById(int id);
    List<Resource> findAllByRegionId(int regionId);
    List<Resource> findAllByRegionIdAndActivatedForScheduling(int regionId, boolean activatedForScheduling);
    Resource findByRegionIdAndFirstNameAndLastNameAndActivatedForScheduling(int regionId, String firstName, String lastName, boolean activatedForScheduling);
    @Query(nativeQuery = true, value = "SELECT r.* FROM resources r INNER JOIN resource_languages rl ON r.id = rl.resource_id INNER JOIN capabilities c ON r.id = c.resource_id WHERE r.region_id = :regionId AND rl.language_id = :languageId AND c.learning_session_id = :learningSessionId")
    List<Resource> findResourceByLanguageIdAndLearningSessionIdAndRegionId(int languageId, int learningSessionId, int regionId);
}
