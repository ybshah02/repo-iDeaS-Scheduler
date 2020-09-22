package com.ideas.scheduler.resource.service;

import javax.persistence.*;

@Entity
@Table(name="capabilities")
public class Capabilities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="resource_id")
    private int resourceId;
    @Column(name="learning_session_id")
    private int learningSessionId;
    @Column(name="capable")
    private boolean capable;

    protected Capabilities(){}

    public Capabilities(int resourceId, int learningSessionId) {
        this.resourceId = resourceId;
        this.learningSessionId = learningSessionId;
        this.capable = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getResourceId() { return resourceId; }

    public void setResourceId(int resourceId) { this.resourceId = resourceId; }

    public int getLearningSessionId() {
        return learningSessionId;
    }

    public void setLearningSessionId(int learningSessionId) {
        this.learningSessionId = learningSessionId;
    }

    public boolean isCapable() {
        return capable;
    }

    public void setCapable(boolean capable) {
        this.capable = capable;
    }
}
