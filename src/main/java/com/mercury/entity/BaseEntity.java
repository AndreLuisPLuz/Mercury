package com.mercury.entity;

import java.util.Date;
import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(name = "createdAt")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    
    @Column(name = "updatedAt")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;

    public BaseEntity() {
        createdAt = new Date();
        updatedAt = new Date();
    }
    
    public Long getId() {
        return id;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
