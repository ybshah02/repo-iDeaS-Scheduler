package com.ideas.scheduler.scheduling.repository;

import com.ideas.scheduler.scheduling.service.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer> {
    Booking findById(int id);
    List<Booking> findAllByRegionId(int regionId);
    List<Booking> findAllByResourceId(int resourceId);
}
