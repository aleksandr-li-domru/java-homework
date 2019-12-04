package com.geekbrains.traning.tasks.repositories;


import com.geekbrains.traning.tasks.entities.Status;
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
public class TaskRepository {
    private MySessionFactory mySessionFactory;

    @Autowired
    public void setMySessionFactory(MySessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }

    public void addTask(Task task) {
        Session session = mySessionFactory.getSession();
        session.save(task);
    }

    public void updateTask(Task task) {
        Session session = mySessionFactory.getSession();
        session.update(task);
    }

    public List<Task> getAllTasks() {
        Session session = mySessionFactory.getSession();
        List<Task> res = session.createQuery("select t from Task t", Task.class).getResultList();
        return res;
    }

    public List<Task> getTasksByTitle(String title) {
        Session session = mySessionFactory.getSession();
        List<Task> res = session.createQuery("select t from Task t where t.title like :title", Task.class)
                .setParameter("title", title)
                .getResultList();
        return res;
    }

    public List<Task> getTasksByStatus(Status status) {
        Session session = mySessionFactory.getSession();
        List<Task> res = session.createQuery("select t from Task t where t.status = :status", Task.class)
                .setParameter("status", status)
                .getResultList();
        return res;
    }

    public void delTask(Long id) {
        Session session = mySessionFactory.getSession();
        session.delete(session.get(Task.class, id));
    }

    public Task getTask(Long id) {
        Session session = mySessionFactory.getSession();
        Task task = session.get(Task.class, id);
        return task;
    }
}
