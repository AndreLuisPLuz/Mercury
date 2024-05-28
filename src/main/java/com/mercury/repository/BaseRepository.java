package com.mercury.repository;

import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.mercury.entity.BaseEntity;
import com.mercury.interfaces.IRepository;

public class BaseRepository<T extends BaseEntity> implements IRepository<T> {
    private EntityManager em;
    private Class<T> entityClass;

    public BaseRepository(Class<T> entityClass, EntityManager em) {
        this.em = em;
        this.entityClass = entityClass;
    }

    public CompletableFuture<T> create(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity cannot be null on creation.");

        em.persist(entity);

        return CompletableFuture.supplyAsync(() -> entity);
    }

    public CompletableFuture<T> select(Long id) {
        em.find(entityClass.getClass(), id);
    }
}
