package com.geekbrains.traning.tasks.services;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService {
    private StatusRepository statusRep;

    @Autowired
    public void setStatusRep(StatusRepository statusRep) {
        this.statusRep = statusRep;
    }

    public Status getStatusById(Long id) {
        return statusRep.findById(id).get();
    }

    public List<Status> getStatuses() {
        return statusRep.findAll();
    }
}
