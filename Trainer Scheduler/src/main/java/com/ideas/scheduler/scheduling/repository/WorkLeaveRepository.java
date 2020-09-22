package com.ideas.scheduler.scheduling.repository;

import com.ideas.scheduler.scheduling.service.WorkLeave;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkLeaveRepository extends CrudRepository<WorkLeave, Integer> {
    List<WorkLeave> findAllByRegionId(int regionId);
    WorkLeave findByRegionIdAndResourceId(int regionId, int resourceId);
    void deleteByRegionIdAndResourceId(int regionId, int resourceId);
}
