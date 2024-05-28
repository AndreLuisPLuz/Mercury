package com.mercury.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CollectionData")

public class CollectionEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "ProjectId")
    private ProjectEntity projectEntity;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "EnvironmentJson", length=2000)
    private String environmentJson;
    
    
    public CollectionEntity(ProjectEntity projectEntity, String name, String environmentJson)
    {
        this.projectEntity = projectEntity;
        this.name = name;
        this.environmentJson = environmentJson;
    }
    
    public ProjectEntity getProjectEntity() {
        return projectEntity;
    }
    
    public void setProjectEntity(ProjectEntity projectEntity) {
        this.projectEntity = projectEntity;
    }
    
    public String getName() {
        return name;
    }
    
    public String getEnvironmentJson() {
        return environmentJson;
    }

    public void setEnvironmentJson(String environmentJson) {
        this.environmentJson = environmentJson;
    }
    public void setName(String name) {
        this.name = name;
    }
}
