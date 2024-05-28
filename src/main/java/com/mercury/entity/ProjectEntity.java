package com.mercury.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectEntity")

public class ProjectEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "UserId")
    private UserEntity userEntity;


    @Column (name = "ProjectName")
    private String userId;
    
    
    @Column (name = "Description")
    private String description;    
    
    
    public ProjectEntity(UserEntity userEntity, String userId, String description)
    {
        super();
        
        this.userEntity = userEntity;
        this.userId = userId;
        this.description = description;        
    }
    
    public UserEntity getUserEntity() {
        return userEntity;
    }
    
    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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
}
