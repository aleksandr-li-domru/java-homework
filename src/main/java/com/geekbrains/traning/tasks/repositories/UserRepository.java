package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
