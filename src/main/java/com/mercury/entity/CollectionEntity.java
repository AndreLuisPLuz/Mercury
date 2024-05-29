package com.mercury.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CollectionEntity")
public class CollectionEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "ProjectId", nullable = false)
    private ProjectEntity project;
    
    @Column(name = "Name")
    private String name;
    
    @Column(name = "EnvironmentJson", length=2000)
    private String environmentJson;
    
    @OneToMany(mappedBy = "collection")
    private Set<NodeEntity> nodes;

    public CollectionEntity() { }

    public CollectionEntity(ProjectEntity projectEntity, String name, String environmentJson)
    {
        this.project = projectEntity;
        this.name = name;
        this.environmentJson = environmentJson;
    }
    
    public ProjectEntity getProject() {
        return project;
    }
    
    public void setProject(ProjectEntity projectEntity) {
        this.project = projectEntity;
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

    public Set<NodeEntity> getNodes() {
        return nodes;
    }
}
