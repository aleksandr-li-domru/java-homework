package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Status;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class DictRepositoryHibernate implements DictRepository {
    private SessionFactory factory;
    private Session session;

    public DictRepositoryHibernate() {
    }

    @Autowired
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Status> getStatuses() {
        session = factory.getCurrentSession();
        List<Status> res = session.createQuery("select s from Status s", Status.class).getResultList();
        return res;
    }

    @Override
    public Status getStatusByName(String name) {
        session = factory.getCurrentSession();
        Status status = session.createQuery("select s from Status s where s.name = :name", Status.class)
                .setParameter("name", name)
                .getSingleResult();
        return status;
    }
}
