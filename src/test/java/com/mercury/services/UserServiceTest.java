package com.mercury.services;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import com.mercury.entity.UserEntity;

public class UserServiceTest {
    @Test
    public void createUserWithParametersTest() {
        UserService userService = new UserService();
        UserEntity user = new UserEntity("Trevis", "trevis@gmail.com", "trevis123");

        assertEquals(userService.insertUser(user), true);
    }

    @Test
    public void createUserPatameterlessTest() {
        UserEntity user = new UserEntity();
        user.setUsername("Niltis");
        user.setEmail("niltis@gmail.com");
        user.setPassword("niltis123");

        UserService userService = new UserService();
        assertEquals(userService.insertUser(user), true);;
    }

    @Test
    public void fetchUserTest() {
        UserEntity user = new UserEntity("Zézin", "zezin@email.com", "zezin123");
        UserService userService = new UserService();
        userService.insertUser(user);

        UserEntity fetchedUser = assertDoesNotThrow(() -> {
            return userService.getUserByUsername("Zézin").get();
        });

        assertEquals(fetchedUser.equals(user), true);
    }

    @Test
    public void validateUserLoginTest() {
        UserEntity user = new UserEntity("Joe", "joe@email.com", "joe123");
        UserService userService = new UserService();
        userService.insertUser(user);

        Boolean isValidLogin = assertDoesNotThrow(() -> {
            return userService.isLoginAttemptValid("Joe", "joe123").get();
        });

        assertEquals(isValidLogin, true);
    }

    @Test
    public void unvalidateUserLoginTest() {
        UserEntity user = new UserEntity("Hannah", "hannah@email.com", "hannah123");
        UserService userService = new UserService();
        userService.insertUser(user);

        Boolean isValidLogin = assertDoesNotThrow(() -> {
            return userService.isLoginAttemptValid("Joe", "joe123").get();
        });

        assertEquals(isValidLogin, false);
    }
}
