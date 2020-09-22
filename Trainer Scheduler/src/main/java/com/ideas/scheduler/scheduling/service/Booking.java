package com.ideas.scheduler.scheduling.service;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name="resource_id")
    int resourceId;

    @Column(name="client_hotel_id")
    int clientId;

    @Column(name="language_id")
    int languageId;

    @Column(name="learning_group_id")
    int learningGroupId;

    @Column(name="learning_session_id")
    int learningSessionId;

    @Column(name="start_datetime")
    LocalDateTime startDateTime;

    @Column(name="end_datetime")
    LocalDateTime endDateTime;

    @Column(name="booking_status")
    boolean bookingStatus;

    //Region where the meeting is occurring
    @Column(name="region_id")
    int regionId;

    @Column(name="session_start_time")
    LocalTime sessionStartTime;

    @Column(name="session_end_time")
    LocalTime sessionEndTime;

    @Column(name="in_person")
    boolean inPerson;

    protected Booking(){}

    public Booking(int resourceId, int clientId, int languageId, int learningSessionId, LocalDateTime startDateTime, LocalDateTime endDateTime,
                   int regionId, LocalTime sessionStartTime, LocalTime sessionEndTime, boolean inPerson, int learningGroupId) {
        this.resourceId = resourceId;
        this.clientId = clientId;
        this.languageId = languageId;
        this.learningGroupId = learningGroupId;
        this.learningSessionId = learningSessionId;
        this.bookingStatus = true;
        this.regionId = regionId;
        this.sessionStartTime = sessionStartTime;
        this.sessionEndTime = sessionEndTime;
        this.inPerson = inPerson;
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

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getLearningGroupId() {
        return learningGroupId;
    }

    public void setLearningGroupId(int learningGroupId) {
        this.learningGroupId = learningGroupId;
    }

    public int getLearningSessionId() {
        return learningSessionId;
    }

    public void setLearningSessionId(int learningSessionId) {
        this.learningSessionId = learningSessionId;
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

    public boolean getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatusTrue() {
        this.bookingStatus = true;
    }

    public void setBookingStatusFalse() {
        this.bookingStatus = false;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public LocalTime getSessionStartTime() {
        return sessionStartTime;
    }

    public void setSessionStartTime(LocalTime sessionStartTime) {
        this.sessionStartTime = sessionStartTime;
    }

    public LocalTime getSessionEndTime() {
        return sessionEndTime;
    }

    public void setSessionEndTime(LocalTime sessionEndTime) {
        this.sessionEndTime = sessionEndTime;
    }

    public boolean isInPerson() { return inPerson; }

    public void setInPerson(boolean inPerson) { this.inPerson = inPerson; }
}
