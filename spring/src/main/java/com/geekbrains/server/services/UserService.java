package com.geekbrains.server.services;

import com.geekbrains.gwt.common.UserDto;
import com.geekbrains.server.entities.User;
import com.geekbrains.server.mappers.UserMapper;
import com.geekbrains.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRep;

    @Autowired
    public void setUserRep(UserRepository userRep) {
        this.userRep = userRep;
    }

    public User getUserById(Long id) {
        return userRep.findById(id).get();
    }

    public List<User> getUsers() {
        return userRep.findAll();
    }

    public List<UserDto> getUsersDto () {
        return UserMapper.MAPPER.fromUserList(getUsers());
    }
}
