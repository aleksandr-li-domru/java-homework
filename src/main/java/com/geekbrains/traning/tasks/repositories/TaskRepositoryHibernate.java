package com.geekbrains.traning.tasks.repositories;


import com.geekbrains.traning.tasks.entities.Task;
import lombok.NoArgsConstructor;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@NoArgsConstructor
public class TaskRepositoryHibernate implements TaskRepository {
    private MySessionFactory mySessionFactory;

    @Autowired
    public void setMySessionFactory(MySessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }

    @Override
    public void addTask(Task task) {
        Session session = mySessionFactory.getSession();
        session.save(task);
    }

    @Override
    public void updateTask(Task task) {
        Session session = mySessionFactory.getSession();
        session.update(task);
    }

    @Override
    public List<Task> getAllTasks() {
        Session session = mySessionFactory.getSession();
        List<Task> res = session.createQuery("select t from Task t", Task.class).getResultList();
        return res;
    }

    @Override
    public void delTask(Long id) {
        Session session = mySessionFactory.getSession();
        session.delete(session.get(Task.class, id));
    }

    @Override
    public Task getTask(Long id) {
        Session session = mySessionFactory.getSession();
        Task task = session.get(Task.class, id);
        return task;
    }
}
