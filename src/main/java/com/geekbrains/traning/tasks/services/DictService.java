package com.geekbrains.traning.tasks.services;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.repositories.DictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictService {
    private DictRepository dictRep;

    @Autowired
    public void setDictRep(DictRepository dictRep) {
        this.dictRep = dictRep;
    }

    public Status getStatusById(Long id) {
        return dictRep.getStatusById(id);
    }

    public Status getStatusByName(String name) {
        return dictRep.getStatusByName(name);
    }

    public List<Status> getStatuses() {
        return dictRep.getStatuses();
    }
}
