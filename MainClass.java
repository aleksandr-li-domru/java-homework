package com.geekbrains.traning.tasks;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.SQLException;

public class MainClass {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        try {
            TaskRepositoryHibernate repository = new TaskRepositoryHibernate(factory);
            TaskService taskService = new TaskService(repository);

            for (int i = 1; i <= 10; i++) {
                Task task = new Task(
                        null,
                        "Task " + i,
                        taskService.getUserByName("admin"),
                        taskService.getUserByName("user"),
                        "description " + i,
                        taskService.getStatusByName("new")
                );
                taskService.addTask(task);
            }
            taskService.printTasks();
        } finally {
            factory.close();
        }
    }
}