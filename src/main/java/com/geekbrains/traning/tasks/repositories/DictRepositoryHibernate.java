package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Status;
import lombok.NoArgsConstructor;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
@NoArgsConstructor
public class DictRepositoryHibernate implements DictRepository {
    private MySessionFactory mySessionFactory;

    @Autowired
    public void setMySessionFactory(MySessionFactory mySessionFactory) {
        this.mySessionFactory = mySessionFactory;
    }

    @Override
    public Status getStatusById(Long id) {
        Session session = mySessionFactory.getSession();
        Status status = session.get(Status.class, id);
        return status;
    }

    @Override
    public Status getStatusByName(String name) {
        Session session = mySessionFactory.getSession();
        Status status = session.createQuery("select s from Status s where s.name = :name", Status.class)
                .setParameter("name", name)
                .getSingleResult();
        return status;
    }

    @Override
    public List<Status> getStatuses() {
        Session session = mySessionFactory.getSession();
        List<Status> res = session.createQuery("select s from Status s", Status.class).getResultList();
        return res;
    }
}
