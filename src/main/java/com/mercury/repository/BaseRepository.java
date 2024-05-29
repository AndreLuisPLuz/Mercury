package com.mercury.repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.Query;

import com.mercury.entity.BaseEntity;
import com.mercury.interfaces.IRepository;

public class BaseRepository<T extends BaseEntity> implements IRepository<T> {
    private static final SessionFactory sf = new AnnotationConfiguration().configure().buildSessionFactory();
    private Class<T> type;

    public BaseRepository(Class<T> type) {
        this.type = type;
    }

    public CompletableFuture<T> create(T entity) {
        if (entity == null)
            throw new IllegalArgumentException("Entity cannot be null on creation.");

        return CompletableFuture.supplyAsync(() -> {
            Session session = sf.openSession();
            Transaction transaction = session.beginTransaction();

            try {
                session.save(entity);
                transaction.commit();

                return entity;
            } catch (HibernateException he) {
                transaction.rollback();
                throw new RuntimeException("Entity could not be saved.");
            } finally {
                session.close();
            }
        });
    }

    public CompletableFuture<T> select(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Session session = sf.openSession();
            Transaction transaction = session.beginTransaction();
    
            try {
                Query selectQuery = session.createQuery(String.format("from %s t where t.id = :id", type.getName()));
                selectQuery.setParameter("id", id);
    
                List<T> entities = selectQuery.list();
                transaction.commit();

                return entities.isEmpty() ? null : entities.get(0);
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw new RuntimeException(e);
            } finally {
                session.close();
            }
        });
    }

    public CompletableFuture<List<T>> selectMany() {
        return CompletableFuture.supplyAsync(() -> {
            Session session = sf.openSession();
            Transaction transaction = session.beginTransaction();
    
            Query selectQuery = session.createQuery(String.format("from %s", type.getName()));
    
            List<T> entities = selectQuery.list();
            transaction.commit();
            
            return entities;
        });
    }

    public CompletableFuture<Boolean> delete(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Session session = sf.openSession();
            Transaction transaction = session.beginTransaction();
    
            try {    
                Query selectQuery = session.createQuery(String.format("from %s t where t.id = :id", type.getName()));
                selectQuery.setParameter("id", id);
    
                List<T> entities = selectQuery.list();
                T entity = entities.get(0);
    
                session.delete(entity);
                transaction.commit();
    
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                session.close();
            }
        });
    }

    public CompletableFuture<Boolean> delete(T entity) {
        return CompletableFuture.supplyAsync(() -> {
            Session session = sf.openSession();
            Transaction transaction = session.beginTransaction();
    
            try {        
                session.delete(entity);
                transaction.commit();
    
                return true;
            } catch (Exception e) {
                transaction.rollback();
                return false;
            } finally {
                session.close();
            }
        });
    }

    public CompletableFuture<T> update(T entity) {
        return CompletableFuture.supplyAsync(() -> {
            Session session = sf.openSession();
            Transaction transaction = session.beginTransaction();
    
            try {
                session.update(entity);
                transaction.commit();

                return entity;
            } catch (HibernateException he) {
                transaction.rollback();
                throw new RuntimeException("Couldn't update entity.");
            } finally {
                session.close();
            }
        });
    }
}
