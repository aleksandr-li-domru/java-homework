package com.geekbrains.traning.tasks.entities;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
