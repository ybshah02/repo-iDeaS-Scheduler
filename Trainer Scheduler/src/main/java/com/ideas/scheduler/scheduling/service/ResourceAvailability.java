package com.ideas.scheduler.scheduling.service;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="resource_availability")
public class ResourceAvailability {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;

    @Column(name="resource_id")
    int resourceId;

    @Column(name="working_day")
    LocalDate workingDay;

    @Column(name="start_time")
    LocalTime startTime;

    @Column(name="end_time")
    LocalTime endTime;

    @Column(name="on_leave")
    boolean onLeave;

    protected ResourceAvailability(){}

    public ResourceAvailability(int resourceId, LocalDate workingDay, LocalTime startTime, LocalTime endTime, boolean onLeave) {
        this.resourceId = resourceId;
        this.workingDay = workingDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.onLeave = onLeave;
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

    public LocalDate getWorkingDay() {
        return workingDay;
    }

    public void setWorkingDay(LocalDate workingDay) {
        this.workingDay = workingDay;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public boolean isOnLeave() {
        return onLeave;
    }

    public void setOnLeave(boolean onLeave) {
        this.onLeave = onLeave;
    }
}
