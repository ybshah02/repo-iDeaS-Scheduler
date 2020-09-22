package com.ideas.scheduler.learning_session.service;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name="learning_sessions")
public class LearningSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="active")
    private boolean active;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "learning_group_id")
    private int learningGroupId;
    @Column(name = "duration")
    private LocalTime duration;
    @Column(name = "pre_duration")
    private LocalTime preDuration;
    @Column(name = "post_duration")
    private LocalTime postDuration;

    protected LearningSession() {}

    public LearningSession(String name, String code, int learningGroupId, LocalTime duration, LocalTime preDuration, LocalTime postDuration) {
        this.active = true;
        this.name = name;
        this.code = code;
        this.learningGroupId = learningGroupId;
        this.duration = duration;
        this.preDuration = preDuration;
        this.postDuration = postDuration;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public boolean isActive(){ return active; }

    public void setActive(){ this.active = true; }

    public void setInactive() { this.active = false; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLearningGroupId() {
        return learningGroupId;
    }

    public void setLearningGroupId  (int learningGroupId) {
        this.learningGroupId = learningGroupId;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public LocalTime getPreDuration() {
        return preDuration;
    }

    public void setPreDuration(LocalTime preDuration) {
        this.preDuration = preDuration;
    }

    public LocalTime getPostDuration() {
        return postDuration;
    }

    public void setPostDuration(LocalTime postDuration) {
        this.postDuration = postDuration;
    }
}
