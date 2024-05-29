package com.mercury.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectEntity")
public class ProjectEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private UserEntity user;

    @Column (name = "ProjectName")
    private String userId;
    
    @Column (name = "Description")
    private String description; 
    
    @OneToMany(mappedBy = "project")
    private Set<CollectionEntity> collections;

    public ProjectEntity() { }
    
    public ProjectEntity(UserEntity userEntity, String userId, String description)
    {
        super();
        
        this.user = userEntity;
        this.userId = userId;
        this.description = description;        
    }
    
    public UserEntity getUser() {
        return user;
    }
    
    public void setUser(UserEntity userEntity) {
        this.user = userEntity;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CollectionEntity> getCollections() {
        return collections;
    }
}
