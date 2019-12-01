package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Status;
import java.util.List;

public interface DictRepository {
    List<Status> getStatuses();
    Status getStatusByName(String name);
}
