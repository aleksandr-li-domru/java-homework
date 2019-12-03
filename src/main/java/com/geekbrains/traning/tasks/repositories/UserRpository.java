package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.User;
import java.util.List;

public interface UserRpository {
    User getUserById(Long id);
    User getUserByName(String name);
    List<User> getUsers();
}
