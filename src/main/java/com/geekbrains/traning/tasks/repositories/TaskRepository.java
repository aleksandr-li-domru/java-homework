package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.entities.Task;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@NoArgsConstructor
public class TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void addTask(Task task) {
        entityManager.persist(task);
    }

    public void updateTask(Task task) {
        entityManager.persist(task);
    }

    public List<Task> getAllTasks(String title, Status status) {
        List<Task> tasks = null;
        if (title != null && title.length() != 0) {
            tasks = entityManager.createQuery("select t from Task t where t.title like :title", Task.class)
                    .setParameter("title", "%" + title + "%")
                    .getResultList();
        } else if (status != null) {
            tasks = entityManager.createQuery("select t from Task t where t.status = :status", Task.class)
                    .setParameter("status", status)
                    .getResultList();
        } else {
            tasks = entityManager.createQuery("select t from Task t", Task.class).getResultList();
        }
        return tasks;
    }

    public void delTask(Long id) {
        entityManager.remove(getTask(id));
    }

    public Task getTask(Long id) {
        return entityManager.find(Task.class, id);
    }
}
