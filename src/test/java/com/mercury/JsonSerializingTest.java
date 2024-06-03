package com.mercury;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.mercury.classes.serializing.JsonSerializer;
import com.mercury.entity.UserEntity;
import com.mercury.services.UserService;

public class JsonSerializingTest {
    @Test
    public void serializeTest() {
        UserEntity user = new UserEntity("John", "john@email.com", "john123");
        UserService userService = new UserService();

        assertDoesNotThrow(() -> userService.insertUser(user).get());

        JsonSerializer<UserEntity> serializer = new JsonSerializer<>(UserEntity.class);

        String jsonStr = serializer.serialize(user);
        UserEntity result = serializer.deserialize(jsonStr);

        assertTrue(user.equals(result));

        userService.deleteUser(user.getId());
    }

    @Test
    public void selfSerializeTest() {
        UserEntity user = new UserEntity("John", "john@email.com", "john123");
        String jsonStr = user.serialize();

        assertTrue(jsonStr != null);
    }
}
