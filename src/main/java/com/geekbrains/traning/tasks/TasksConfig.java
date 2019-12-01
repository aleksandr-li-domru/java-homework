package com.geekbrains.traning.tasks;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan(basePackages = {"com.geekbrains.traning.tasks"})
public class TasksConfig {

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory() {
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        return factory;
    }
}
