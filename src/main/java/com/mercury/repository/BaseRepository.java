package com.mercury.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;

import com.mercury.entity.BaseEntity;
import com.mercury.interfaces.IRepository;
import com.mercury.types.TypeToken;

public class BaseRepository<T extends BaseEntity> implements IRepository<T> {
    private static final SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private TypeToken<T> type;

    public BaseRepository() { }

    public CompletableFuture<T> create(T entity) throws Exception {
        if (entity == null)
            throw new IllegalArgumentException("Entity cannot be null on creation.");

        Session session = sf.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(entity);
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            throw new Exception("Entity could not be saved.");
        }

        return CompletableFuture.supplyAsync(() -> entity);
    }

    public CompletableFuture<T> select(Long id) {
        Session session = sf.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Class<?> entityClass = type.getClass();

        Query selectQuery = session.createQuery(String.format("from %s t where t.id = :id", entityClass.getName()));
        selectQuery.setParameter("id", id);

        List<T> entities = selectQuery.list();
        transaction.commit();
        session.close();

        return CompletableFuture.supplyAsync(() -> entities.get(0));
    }

    public CompletableFuture<List<T>> selectMany() {
        Session session = sf.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Class<?> entityClass = type.getClass();

        Query selectQuery = session.createQuery(String.format("from %s", entityClass.getName()));

        List<T> entities = selectQuery.list();
        transaction.commit();
        session.close();

        return CompletableFuture.supplyAsync(() -> entities);
    }

    public CompletableFuture<Boolean> delete(Long id) {
        Supplier<Boolean> supplier;
        
        Session session = sf.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            Class<?> entityClass = type.getClass();

            Query selectQuery = session.createQuery(String.format("from %s t where t.id = :id", entityClass.getName()));
            selectQuery.setParameter("id", id);

            List<T> entities = selectQuery.list();
            T entity = entities.get(0);

            session.delete(entity);

            transaction.commit();
            session.close();

            supplier = (() -> true);
        } catch (Exception e) {
            transaction.rollback();
            supplier = (() -> false);
        } finally {
            session.close();
        }

        return CompletableFuture.supplyAsync(supplier);
    }

    public CompletableFuture<Boolean> delete(T entity) {
        Supplier<Boolean> supplier;
        
        Session session = sf.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.delete(entity);

            transaction.commit();
            session.close();

            supplier = (() -> true);
        } catch (Exception e) {
            transaction.rollback();
            supplier = (() -> false);
        } finally {
            session.close();
        }

        return CompletableFuture.supplyAsync(supplier);
    }

    public CompletableFuture<T> update(T entity) throws Exception {
        Session session = sf.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.update(entity);
            transaction.commit();
        } catch (HibernateException he) {
            transaction.rollback();
            throw new Exception("Couldn't update entity.");
        } finally {
            session.close();
        }

        return CompletableFuture.supplyAsync(() -> entity);
    }
}
