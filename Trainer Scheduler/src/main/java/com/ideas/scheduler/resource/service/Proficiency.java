package com.ideas.scheduler.resource.service;

import javax.persistence.*;

@Entity
@Table(name="proficiencies")
public class Proficiency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="proficiency")
    private String proficiency;

    protected Proficiency(){}

    public Proficiency(int id, String proficiency) {
        this.id = id;
        this.proficiency = proficiency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}
