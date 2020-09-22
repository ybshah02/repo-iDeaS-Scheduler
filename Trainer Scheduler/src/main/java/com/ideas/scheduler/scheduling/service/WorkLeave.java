package com.ideas.scheduler.scheduling.service;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="work_leave")
public class WorkLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="resource_id")
    int resourceId;

    @Column(name="region_id")
    int regionId;

    @Column(name="reason_code")
    int reason_code;

    @Column(name="start_date")
    LocalDateTime startDateTime;

    @Column(name="end_date")
    LocalDateTime endDateTime;

    protected WorkLeave(){}

    public WorkLeave(int resourceId, int regionId, int reason_code, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.resourceId = resourceId;
        this.regionId = regionId;
        this.reason_code = reason_code;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
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

    public int getReason_code() {
        return reason_code;
    }

    public void setReason_code(int reason_code) {
        this.reason_code = reason_code;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }
}
