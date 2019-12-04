package com.geekbrains.traning.tasks.services;

import com.geekbrains.traning.tasks.entities.User;
import com.geekbrains.traning.tasks.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private UserRepository userRep;

    @Autowired
    public void setUserRep(UserRepository userRep) {
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
