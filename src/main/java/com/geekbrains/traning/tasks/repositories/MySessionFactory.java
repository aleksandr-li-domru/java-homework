package com.geekbrains.traning.tasks.repositories;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;

@Component
public class MySessionFactory {
    private EntityManagerFactory entityManagerFactory;
    private Session session;

    @Autowired
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        session = this.entityManagerFactory.unwrap(SessionFactory.class).openSession();
    }

    public Session getSession() {
        return session;
    }
}
