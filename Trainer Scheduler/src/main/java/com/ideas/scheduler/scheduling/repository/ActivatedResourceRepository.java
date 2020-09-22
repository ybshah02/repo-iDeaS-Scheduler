package com.ideas.scheduler.scheduling.repository;

import com.ideas.scheduler.scheduling.service.ActivatedResource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivatedResourceRepository extends CrudRepository<ActivatedResource, Integer> {

    ActivatedResource findByResourceId(int resourceId);
    ActivatedResource findByRegionIdAndResourceId(int regionId, int resourceId);
    List<ActivatedResource> findAllByRegionId(int regionId);
    void deleteByResourceId(int resourceId);
}
