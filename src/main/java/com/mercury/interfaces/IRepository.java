package com.mercury.interfaces;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.mercury.entity.BaseEntity;

public interface IRepository<T extends BaseEntity> {
    CompletableFuture<T> create(T entity);
    CompletableFuture<T> update(T entity);
    CompletableFuture<Boolean> delete(Long id);
    CompletableFuture<Boolean> delete(T entity);
    CompletableFuture<ArrayList<T>> selectMany();
    CompletableFuture<T> select();
}