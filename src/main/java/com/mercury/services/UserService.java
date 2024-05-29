package com.mercury.services;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import com.mercury.entity.UserEntity;
import com.mercury.interfaces.IUserService;
import com.mercury.repository.Repository;

public class UserService implements IUserService {
    private final Repository<UserEntity> repo = new Repository<>(UserEntity.class);

    public UserService() { }

    public CompletableFuture<Boolean> isLoginAttemptValid(String username, String password) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<UserEntity> usersList = repo.selectMany().get();
                
                for (UserEntity user : usersList) {
                    if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                        return true;
                    }
                }
    
                return false;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public CompletableFuture<UserEntity> getUserByUsername(String username) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                List<UserEntity> usersList = repo.selectMany().get();
                
                for (UserEntity user : usersList) {
                    if (user.getUsername().equals(username)) {
                        return user;
                    }
                }

                throw new RuntimeException("No matching user found.");
            } catch (Exception e) {
                throw new CompletionException(e);
            }
        });
    }
}
