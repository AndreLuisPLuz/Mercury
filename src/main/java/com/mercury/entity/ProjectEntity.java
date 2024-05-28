package com.mercury.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ProjectData")

public class ProjectEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "UserId")
    private UserEntity userEntity;

    @Column (name = "ProjectName")
    private String userId;

    @Column (name = "Description")
    private String description;    
    
}
