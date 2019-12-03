package com.geekbrains.traning.tasks.repositories;


import com.geekbrains.traning.tasks.entities.Task;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class TaskRepositoryHibernate implements TaskRepository {
    private SessionFactory factory;
    private Session session;

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public TaskRepositoryHibernate() {

    }

    @Override
    public void addTask(Task task) {
        session = factory.getCurrentSession();
        session.save(task);
    }

    @Override
    public void updateTask(Task task) {
        session = factory.getCurrentSession();
        session.update(task);
    }

    @Override
    public List<Task> getAllTasks() {
        session = factory.getCurrentSession();
        List<Task> res = session.createQuery("select t from Task t", Task.class).getResultList();
        return res;
    }

    @Override
    public void delTask(Long id) {
        session = factory.getCurrentSession();
        session.delete(session.get(Task.class, id));
    }
}
