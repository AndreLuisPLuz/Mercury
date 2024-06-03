package com.mercury.interfaces;

import java.util.concurrent.CompletableFuture;

import com.mercury.entity.UserEntity;

public interface IUserService {
    CompletableFuture<Boolean> insertUser(UserEntity newUser);
    CompletableFuture<Boolean> isLoginAttemptValid(String username, String password);
    CompletableFuture<UserEntity> getUserByUsername(String username);
    CompletableFuture<Boolean> deleteUser(UserEntity user);
    CompletableFuture<Boolean> deleteUser(Long id);
}
