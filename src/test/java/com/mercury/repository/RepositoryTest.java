package com.mercury.repository;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }

    @Test
    public void updateEntityTest() {
        String initialUsername = "Allan";
        String editedUsername = "No longer Allan";

        UserEntity userToBeUpdated = new UserEntity(initialUsername, "allan@email.com", "allan123");
        Repository<UserEntity> repo = new Repository<>(UserEntity.class);

        assertDoesNotThrow(() -> repo.create(userToBeUpdated).get());

        userToBeUpdated.setUsername(editedUsername);
        UserEntity editedUser = assertDoesNotThrow(() -> {
            return repo.update(userToBeUpdated).get();
        });

        Boolean wasUpdateSuccesful = (userToBeUpdated.equals(editedUser) && userToBeUpdated.getUsername() != editedUser.getUsername());

        assertEquals(wasUpdateSuccesful, true);
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
    }

    @Test
    public void selectSingleEntityTest() {
        UserEntity user = new UserEntity("Barry", "barry@email.com", "barry123");
        Repository<UserEntity> repo = new Repository<>(UserEntity.class);

        assertDoesNotThrow(() -> repo.create(user).get());

        Long userId = user.getId();
        UserEntity returnedUser = assertDoesNotThrow(() -> repo.select(userId).get());

        assertEquals(user.equals(returnedUser), true);
    }
}
