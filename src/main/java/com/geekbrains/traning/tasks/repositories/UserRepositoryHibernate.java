package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryHibernate implements UserRpository {
    private SessionFactory factory;
    private Session session;

    public UserRepositoryHibernate() {
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public User getUserByName(String name) {
        session = factory.getCurrentSession();
        User user = session.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        return user;
    }

    @Override
    public List<User> getUsers() {
        session = factory.getCurrentSession();
        List<User> res = session.createQuery("select u from User u", User.class).getResultList();
        return res;
    }
}
