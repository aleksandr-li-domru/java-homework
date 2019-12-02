package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Status;
import java.util.List;

public interface DictRepository {
    Status getStatusById(Long id);
    Status getStatusByName(String name);
    List<Status> getStatuses();
}
