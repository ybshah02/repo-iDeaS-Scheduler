package com.ideas.scheduler.scheduling.repository;

import com.ideas.scheduler.scheduling.service.ResourceAvailability;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ResourceAvailabilityRepository extends CrudRepository<ResourceAvailability, Integer> {
    ResourceAvailability findByResourceIdAndWorkingDay(int resourceId, LocalDate date);
    List<ResourceAvailability> findAllByWorkingDay(LocalDate date);
    List<ResourceAvailability> findAllByResourceId(int resourceId);

}
