package com.mercury.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "UserData")

public class UserEntity extends BaseEntity {

    @Column (name = "Username")
    private String username;

    @Column (name = "Email")
    private String email;
    
    @Column (name = "Password")
    private String password;
    
    public UserEntity(String username, String email, String password)
    {
        super();
        
        this.username = username;
        this.email = email;
        this.password = password;
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
    
    
    
}