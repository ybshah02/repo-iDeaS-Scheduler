package com.ideas.scheduler.client.service;

import javax.persistence.*;

@Entity
@Table(name="client_hotels")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="region_id")
    private int regionId;
    @Column(name="name")
    private String name;
    //8 digit code
    @Column(name="account_code")
    private String accountCode;
    @Column(name="active")
    private boolean active;

    protected Client(){}

    public Client(String name, int regionId, String accountCode) {
        this.regionId = regionId;
        this.name = name;
        this.accountCode = accountCode;
        this.active = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public boolean isActive() { return active; }

    public void setActive() { this.active = true; }

    public void setInactive() { this.active = false; }
}
