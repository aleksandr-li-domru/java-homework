package com.geekbrains.traning.tasks.repositories;

import com.geekbrains.traning.tasks.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}