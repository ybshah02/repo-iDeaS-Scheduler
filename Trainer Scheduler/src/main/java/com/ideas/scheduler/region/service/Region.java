package com.ideas.scheduler.region.service;

import javax.persistence.*;

@Entity
@Table(name="regions")
public class Region {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name="active")
    private boolean active;
    @Column(name="region")
    private String regionName;
    @Column(name="timezone")
    private String timezone;


    protected Region(){}

    public Region(String region, String timezone){
        this.active = true;
        this.regionName = regionName;
        this.timezone = timezone;
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

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String region) {
        this.regionName = region;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

}
