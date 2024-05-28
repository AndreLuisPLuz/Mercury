package com.mercury.entity;

import com.mercury.entity.UserEntity;

public class Authentification {
    private UserEntity user;
    private Boolean userExists = false;

    public UserEntity getUser()
    {
        return this.user;
    }

    public Boolean usersExists()
    {
        return this.userExists;
    }
}
