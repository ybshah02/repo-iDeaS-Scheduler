package com.ideas.scheduler.client.service;

import javax.persistence.*;

@Entity
@Table(name="client_lg_progression")
public class ClientLGProgression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="client_id")
    private int clientId;
    @Column(name="learning_group_id")
    private int learningGroupId;
    @Column(name="learning_session_id")
    private int learningSessionId;
    @Column(name="finished")
    private boolean finished;

    protected ClientLGProgression(){}

    public ClientLGProgression(int clientId, int learningGroupId, int learningSessionId) {
        this.clientId = clientId;
        this.learningGroupId = learningGroupId;
        this.learningSessionId = learningSessionId;
        this.finished = true;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() { return clientId; }

    public void setClientId(int clientId) { this.clientId = clientId; }

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

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
