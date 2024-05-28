package com.mercury.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RequestData")

public class RequestEntity extends BaseEntity {
    @Column(name = "Verb")
    private String verb;

    
    @Column(name = "Nickname")
    private String nickname;
    
    @Column(name = "ContentJason", length = 65535)
    private String contentJson;
    
    
    public RequestEntity(String verb, String nickname, String contentJson)
    {
        super();
        
        this.verb = verb;
        this.nickname = nickname;
        this.contentJson = contentJson;
        
    }
    
    public String getVerb() {
        return verb;
    }
    public void setVerb(String verb) {
        this.verb = verb;
    }
    
    public String getNickname() {
        return nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getContentJson() {
        return contentJson;
    }
    
    public void setContentJson(String contentJson) {
        this.contentJson = contentJson;
    }
}


