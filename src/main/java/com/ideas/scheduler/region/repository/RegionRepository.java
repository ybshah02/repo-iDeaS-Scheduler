package com.ideas.scheduler.region.repository;

import com.ideas.scheduler.region.service.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepository extends CrudRepository<Region, Integer> {

    List<Region> findAll();
    Region findById(int id);
}
