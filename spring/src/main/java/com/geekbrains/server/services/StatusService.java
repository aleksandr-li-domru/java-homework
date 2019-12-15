package com.geekbrains.server.services;

import com.geekbrains.gwt.common.StatusDto;
import com.geekbrains.server.entities.Status;
import com.geekbrains.server.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<StatusDto> getStatusesDto () {
        List<Status> statuses = getStatuses();
        List<StatusDto> res = new ArrayList<StatusDto>();
        for (int i = 0; i < statuses.size(); i++) {
            res.add(statuses.get(i).getDto());
        }
        return res;
    }
}
