package com.ideas.scheduler.learning_group.service;

import javax.persistence.*;

@Entity
@Table(name="learning_groups")
public class LearningGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="active")
    private boolean active;
    @Column(name="code")
    private String code;
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;

    protected LearningGroup(){}

    public LearningGroup(String name, String code, String description) {
        this.active = true;
        this.name = name;
        this.code = code;
        this.description = description;
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

    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }
}
