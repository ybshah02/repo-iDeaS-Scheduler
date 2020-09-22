package com.ideas.scheduler.resource.service;

import javax.persistence.*;

@Entity
@Table(name="resource_languages")
public class ResourceLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="resource_id")
    private int resourceId;
    @Column(name="language_id")
    private int languageId;
    @Column(name="proficiency_id")
    private int proficiencyId;
    @Column(name="active")
    private boolean active;

    protected ResourceLanguage(){}

    public ResourceLanguage(int resourceId, int languageId, int proficiencyId) {
        this.active = true;
        this.resourceId = resourceId;
        this.languageId = languageId;
        this.proficiencyId = proficiencyId;
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

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getProficiencyId() {
        return proficiencyId;
    }

    public void setProficiencyId(int proficiencyId) {
        this.proficiencyId = proficiencyId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive() {
        this.active = true;
    }

    public void setInactive() {
        this.active = false;
    }
}
