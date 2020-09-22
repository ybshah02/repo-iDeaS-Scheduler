package com.ideas.scheduler.resource.service;

import javax.persistence.*;

@Entity
@Table(name = "resources")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="active")
    private boolean active;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "region_id")
    private int regionId;
    @Column(name="activated_for_scheduling")
    private boolean activatedForScheduling;

    protected Resource() {
    }

    public Resource(String firstName, String lastName, String email, int regionId) {
        this.active = true;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.regionId = regionId;
    }

    public int getID() { return id;}

    public void setID(int id) {this.id = id; }

    public boolean getActive(){ return active; }

    public void setActive(){ this.active = true; }

    public void setInactive() { this.active = false; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public boolean isActivatedForScheduling() {
        return activatedForScheduling;
    }

    public void setActivatedForSchedulingTrue() {
        this.activatedForScheduling = true;
    }

    public void setActivatedForSchedulingFalse() {
        this.activatedForScheduling = false;
    }
}
