package com.geekbrains.traning.tasks;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainClass {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TasksConfig.class);
        TaskService taskService = context.getBean("taskService", TaskService.class);

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
        context.close();
    }
}