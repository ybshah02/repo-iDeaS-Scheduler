package com.ideas.scheduler.scheduling.service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="activated_resources")
public class ActivatedResource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="resource_id")
    int resourceId;

    @Column(name="region_id")
    int regionId;

    @Column(name="start_date")
    LocalDateTime startDate;

    @Column(name="end_date")
    LocalDateTime endDate;

    protected ActivatedResource(){}

    public ActivatedResource(int resourceId, int regionId, LocalDateTime startDate, LocalDateTime endDate) {
        this.resourceId = resourceId;
        this.regionId = regionId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
