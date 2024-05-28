package com.mercury.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "NodeData")

public class NodeEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "CollectorId")
    private CollectionEntity collectionEntity;
    
    @OneToOne
    @JoinColumn(name ="ParentNodeId", nullable = true)
    private NodeEntity nodeEntity;
    
    @OneToMany
    @JoinColumn(name = "RequestId")
    private RequestEntity requestEntity;
    
    @Column(name = "name")
    private String name;
    
    
    @Column(name = "Type")
    private Boolean type;
    
    public NodeEntity(CollectionEntity collectionEntity, NodeEntity nodeEntity, RequestEntity requestEntity, String name,  Boolean type)
    {
        super();
        
        this.collectionEntity = collectionEntity;
        this.nodeEntity = nodeEntity;
        this.requestEntity = requestEntity;
        this.name = name;
        this.type = type;
    }
    
    public CollectionEntity getCollectionEntity() {
        return collectionEntity;
    }
    
    public void setCollectionEntity(CollectionEntity collectionEntity) {
        this.collectionEntity = collectionEntity;
    }
    
    public NodeEntity getNodeEntity() {
        return nodeEntity;
    }
    
    public void setNodeEntity(NodeEntity nodeEntity) {
        this.nodeEntity = nodeEntity;
    }
    
    public RequestEntity getRequestEntity() {
        return requestEntity;
    }
    
    public void setRequestEntity(RequestEntity requestEntity) {
        this.requestEntity = requestEntity;
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
