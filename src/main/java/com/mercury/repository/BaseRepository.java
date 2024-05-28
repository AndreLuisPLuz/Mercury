package com.mercury.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.mercury.entity.BaseEntity;
import com.mercury.interfaces.IRepository;
import com.mercury.types.TypeToken;

public class BaseRepository<T extends BaseEntity> implements IRepository<T> {
    private EntityManager em;
    private TypeToken<T> type;

    public BaseRepository(EntityManager em) {
        this.em = em;
    }

    public CompletableFuture<T> create(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity cannot be null on creation.");

        em.persist(entity);

        return CompletableFuture.supplyAsync(() -> entity);
    }

    public CompletableFuture<T> select(Long id) {
        Class<?> entityClass = type.getClass();
        return CompletableFuture.supplyAsync(() -> (T)em.find(entityClass, id));
    }

    public CompletableFuture<List<T>> selectMany() {
        Class<?> entityClass = type.getClass();

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = (CriteriaQuery<T>)criteriaBuilder.createQuery(entityClass);
        Root<T> root = (Root<T>)criteriaQuery.from(entityClass);

        criteriaQuery.select(root);

        Query query = em.createQuery(criteriaQuery);
        return CompletableFuture.supplyAsync(() -> query.getResultList());
    }

    public CompletableFuture<Boolean> delete(Long id) {
        Supplier<Boolean> supplier;
        Class<?> entityClass = type.getClass();

        try {
            T entity = (T)em.find(entityClass, id);
            em.remove(entity);

            supplier = (() -> true);
        } catch (Exception e) {
            supplier = (() -> false);
        }

        return CompletableFuture.supplyAsync(supplier);
    }

    public CompletableFuture<Boolean> delete(T entity) {
        Supplier<Boolean> supplier;

        try {
            em.remove(entity);
            supplier = (() -> true);
        } catch(Exception e) {
            supplier = (() -> false);
        }

        return CompletableFuture.supplyAsync(supplier);
    }

    public CompletableFuture<T> update(T entity) {
        em.merge(entity);
        return CompletableFuture.supplyAsync(() -> entity);
    }
}
