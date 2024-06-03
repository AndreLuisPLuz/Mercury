package com.mercury.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mercury.entity.UserEntity;

public class RepositoryTest {
    @Test
    public void createEntityTest() {
        UserEntity user = new UserEntity("Paul", "paul@email.com", "paul123");
        Repository<UserEntity> repo = new Repository<>(UserEntity.class);

        UserEntity result = assertDoesNotThrow(() -> {
            return repo.create(user).get();
        });

        assertEquals(user.equals(result), true);

        repo.delete(user);
    }

    @Test
    public void updateEntityTest() {
        String initialUsername = "Allan";
        String editedUsername = "No longer Allan";

        UserEntity userToBeUpdated = new UserEntity(initialUsername, "allan@email.com", "allan123");
        Repository<UserEntity> repo = new Repository<>(UserEntity.class);

        assertDoesNotThrow(() -> repo.create(userToBeUpdated).get());
        System.out.println(String.format("Entity ID insire test after repo.create: %d", userToBeUpdated.getId()));

        userToBeUpdated.setUsername(editedUsername);
        UserEntity editedUser = assertDoesNotThrow(() -> {
            return repo.update(userToBeUpdated).get();
        });

        assertTrue(userToBeUpdated.equals(editedUser));
        assertTrue(editedUser.getUsername().equals(editedUsername));

        repo.delete(editedUser);
    }

    @Test
    public void deleteEntityTest() {
        UserEntity user = new UserEntity("Linda", "linda@email.com", "linda123");
        Repository<UserEntity> repo = new Repository<>(UserEntity.class);

        assertDoesNotThrow(() -> repo.create(user).get());

        Long userId = user.getId();

        Boolean deletedWithSuccess = assertDoesNotThrow(() -> repo.delete(userId).get());
        UserEntity returnedUser = assertDoesNotThrow(() -> repo.select(userId).get());

        assertEquals(deletedWithSuccess, true);
        assertEquals(returnedUser, null);

        repo.delete(user);
    }

    @Test
    public void selectSingleEntityTest() {
        UserEntity user = new UserEntity("Barry", "barry@email.com", "barry123");
        Repository<UserEntity> repo = new Repository<>(UserEntity.class);

        assertDoesNotThrow(() -> repo.create(user).get());

        UserEntity returnedUser = assertDoesNotThrow(() -> repo.select(user.getId()).get());

        System.out.println(String.format("user id: %d", user.getId()));
        System.out.println(String.format("returned user id: %d", returnedUser.getId()));

        assertTrue(user.equals(returnedUser));

        repo.delete(user);
    }
}
