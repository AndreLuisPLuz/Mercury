package com.mercury.services;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;

import com.mercury.entity.UserEntity;

public class UserServiceTest {
    @Test
    public void createUserWithParametersTest() {
        UserService userService = new UserService();
        UserEntity user = new UserEntity("Trevis", "trevis@gmail.com", "trevis123");

        Boolean creationWasSuccessful = assertDoesNotThrow(() -> userService.insertUser(user).get());

        assertTrue(creationWasSuccessful);

        userService.deleteUser(user);
    }

    @Test
    public void createUserPatameterlessTest() {
        UserEntity user = new UserEntity();
        user.setUsername("Niltis");
        user.setEmail("niltis@gmail.com");
        user.setPassword("niltis123");

        UserService userService = new UserService();
        Boolean creationWasSuccessful = assertDoesNotThrow(() -> userService.insertUser(user).get());

        assertTrue(creationWasSuccessful);

        userService.deleteUser(user);
    }

    @Test
    public void fetchUserTest() {
        UserEntity user = new UserEntity("Joseph", "joseph@email.com", "joseph123");
        UserService userService = new UserService();
        userService.insertUser(user);

        UserEntity fetchedUser = assertDoesNotThrow(() -> {
            return userService.getUserByUsername("Joseph").get();
        });

        assertTrue(fetchedUser.equals(user));

        userService.deleteUser(user);
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

        userService.deleteUser(user);
    }

    @Test
    public void unvalidateUserLoginTest() {
        UserEntity user = new UserEntity("Hannah", "hannah@email.com", "hannah123");
        UserService userService = new UserService();
        userService.insertUser(user);

        Boolean isValidLogin = assertDoesNotThrow(() -> {
            return userService.isLoginAttemptValid("Joe", "joe123").get();
        });

        assertFalse(isValidLogin);

        userService.deleteUser(user);
    }
}
