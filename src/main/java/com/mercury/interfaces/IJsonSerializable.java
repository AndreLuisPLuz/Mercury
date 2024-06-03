package com.mercury.interfaces;

public interface IJsonSerializable<T extends IJsonSerializable<T>> {
    public String serialize();
}
