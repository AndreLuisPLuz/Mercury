package com.mercury.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NodeEntity")
public class NodeEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "CollectionId")
    private CollectionEntity collection;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RequestId", referencedColumnName = "Id", nullable = true)
    private RequestEntity request;
    
    @OneToOne
    @JoinColumn(name = "parentNodeId", nullable = true)
    private NodeEntity parentNode;

    @OneToOne(mappedBy = "parentNode")
    private NodeEntity childNode;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "Type")
    private Boolean type;

    public NodeEntity() { }
    
    public NodeEntity(CollectionEntity collectionEntity, String name,  Boolean type) {
        super();
        
        this.collection = collectionEntity;
        this.name = name;
        this.type = type;
    }
    
    public CollectionEntity getCollection() {
        return collection;
    }
    
    public void setCollection(CollectionEntity collectionEntity) {
        this.collection = collectionEntity;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Boolean getType() {
        return type;
    }
    
    public void setType(Boolean type) {
        this.type = type;
    }
}
