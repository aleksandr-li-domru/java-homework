package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.User;
import lombok.NoArgsConstructor;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@NoArgsConstructor
public class UserRepository {
    private MySessionFactory mySessionFactory;

    @Autowired
    public void setMySessionFactory(MySessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }

    public User getUserById(Long id) {
        Session session = mySessionFactory.getSession();
        User user = session.get(User.class, id);
        return user;
    }

    public User getUserByName(String name) {
        Session session = mySessionFactory.getSession();
        User user = session.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        return user;
    }

    public List<User> getUsers() {
        Session session = mySessionFactory.getSession();
        List<User> res = session.createQuery("select u from User u", User.class).getResultList();
        return res;
    }
}
