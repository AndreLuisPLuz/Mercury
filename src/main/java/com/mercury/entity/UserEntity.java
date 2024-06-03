package com.mercury.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.mercury.classes.serializing.JsonSerializer;
import com.mercury.interfaces.IJsonSerializable;

@Entity
@Table(name = "UserEntity")
public class UserEntity extends BaseEntity implements IJsonSerializable<UserEntity> {
    @Column (name = "Username")
    private String username;

    @Column (name = "Email")
    private String email;
    
    @Column (name = "Password")
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<ProjectEntity> projects;

    public UserEntity() {}
    
    public UserEntity(String username, String email, String password) {
        super();
        
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UserEntity createFromJson (String jsonStr) {
        JsonSerializer<UserEntity> serializer = new JsonSerializer<>(UserEntity.class);
        return serializer.deserialize(jsonStr);
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String toString() {
        return String.format("%s, %s, %s, %s, %s", getCreatedAt().toString(), getUpdatedAt().toString(), getEmail(), getPassword(), getUsername());
    }

    public Set<ProjectEntity> getProjects() {
        return projects;
    }

    public boolean equals(UserEntity u) {
        return (this.getId().equals(u.getId()));
    }

    public String serialize() {
        JsonSerializer<UserEntity> serializer = new JsonSerializer<>(UserEntity.class);
        return serializer.serialize(this);
    }
}
