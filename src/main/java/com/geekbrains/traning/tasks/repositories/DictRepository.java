package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Status;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@NoArgsConstructor
public class DictRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Status getStatusById(Long id) {
        return entityManager.find(Status.class, id);
    }

    public Status getStatusByName(String name) {
        return entityManager.createQuery("select s from Status s where s.name = :name", Status.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Status> getStatuses() {
        return entityManager.createQuery("select s from Status s", Status.class).getResultList();
    }
}
