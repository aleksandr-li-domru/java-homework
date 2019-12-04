package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.User;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@NoArgsConstructor
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    public User getUserByName(String name) {
        return entityManager.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }
}
