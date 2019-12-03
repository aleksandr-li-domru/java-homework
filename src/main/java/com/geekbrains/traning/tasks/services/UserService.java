package com.geekbrains.traning.tasks.services;

import com.geekbrains.traning.tasks.entities.User;
import com.geekbrains.traning.tasks.repositories.UserRpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRpository userRep;

    @Autowired
    public void setUserRep(UserRpository userRep) {
        this.userRep = userRep;
    }

    public User getUserById(Long id) {
        return userRep.getUserById(id);
    }

    public User getUserByName(String name) {
        return userRep.getUserByName(name);
    }

    public List<User> getUsers() {
        return userRep.getUsers();
    }
}
