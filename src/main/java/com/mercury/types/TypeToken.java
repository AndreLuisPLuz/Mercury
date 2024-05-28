package com.mercury.types;

import java.lang.reflect.*;

public abstract class TypeToken<T> {
    private Type type;

    protected TypeToken() {
        Type superClass = getClass().getGenericSuperclass();
        this.type = ((ParameterizedType)superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}
