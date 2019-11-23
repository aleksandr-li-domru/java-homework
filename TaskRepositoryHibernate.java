package com.geekbrains.traning.tasks;

import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepositoryHibernate implements TaskRepository {
    private SessionFactory factory;
    private Session session;

    @Autowired
    @Qualifier(value = "sessionFactory")
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public TaskRepositoryHibernate() {

    }

    @Override
    public void addTask(Task task) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
    }

    @Override
    public void updateTask(Task task) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
    }

    @Override
    public List<Task> getAllTasks() {
        session = factory.getCurrentSession();
        session.beginTransaction();
        List<Task> res = session.createQuery("select t from Task t", Task.class).getResultList();
        session.getTransaction().commit();
        return res;
    }

    @Override
    public void delTask(Long id) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        session.delete(session.get(Task.class, id));
        session.getTransaction().commit();
    }

    @Override
    public User getUserByName(String name) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        User user = session.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
        session.getTransaction().commit();
        return user;
    }

    @Override
    public Status getStatusByName(String name) {
        session = factory.getCurrentSession();
        session.beginTransaction();
        Status status = session.createQuery("select s from Status s where s.name = :name", Status.class)
                .setParameter("name", name)
                .getSingleResult();
        session.getTransaction().commit();
        return status;
    }
}
