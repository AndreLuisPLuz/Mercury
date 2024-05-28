package com.mercury;

import com.mercury.entity.UserEntity;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.*;

public class Authentification {
    private UserEntity user;
    private Boolean userExists = false;

    public UserEntity getUser() {
        return this.user;
    }

    public Boolean usersExists() {
        return this.userExists;
    }

    public static Authentification tryLogin(String user, String pass) {
        Authentification auth = new Authentification();
        // Cria uma sessão
        Session session = HibernateUtil
        .getSessionFactory()
        .getCurrentSession();

        Transaction transaction = session.beginTransaction();
        // Cria uma query com um parâmetro
        Query query = session.createQuery(
        "from UserData u where u.username = " + user.toString()
        );
        // Chama a query
        List<UserEntity> users = query.list();
        transaction.commit();
        if (users.size() == 0)
            return auth;

        auth.userExists = true;
        UserEntity loggedUser = users.get(0);
        if (!loggedUser.getPassword().equals(pass))
            return auth;
        auth.user = loggedUser;
        return auth;
    }

}
