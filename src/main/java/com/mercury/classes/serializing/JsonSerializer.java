package com.mercury.classes.serializing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercury.interfaces.IJsonSerializable;

public class JsonSerializer<T extends IJsonSerializable<T>> {
    private ObjectMapper mapper = new ObjectMapper();
    private Class<T> type;

    public JsonSerializer(Class<T> type) {
        this.type = type;
    }

    public String serialize(T obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    public T deserialize(String str) {
        try {
            return mapper.readValue(str, type);
        } catch (Exception ex) {
            return null;
        }
    }
}
