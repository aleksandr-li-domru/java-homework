package com.geekbrains.traning.tasks.controllers;

import com.geekbrains.traning.tasks.entities.Status;
import com.geekbrains.traning.tasks.entities.Task;
import com.geekbrains.traning.tasks.entities.User;
import com.geekbrains.traning.tasks.services.DictService;
import com.geekbrains.traning.tasks.services.TaskService;
import com.geekbrains.traning.tasks.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MainController {

    private TaskService taskService;
    private DictService dictService;
    private UserService userService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setDictService(DictService dictService) {
        this.dictService = dictService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String showAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addTask(Model model) {
        Task task = new Task();
        model.addAttribute("task", task);

        List<User> users = userService.getUsers();
        List<Status> statuses = dictService.getStatuses();
        model.addAttribute("users", users);
        model.addAttribute("statuses", statuses);

        return "add";
    }

    @RequestMapping(value = "/add_task_simple", method = RequestMethod.POST)
    public String addNewTaskSimple(String title, String description, Long owner, Long executer, Long status) {
        User own = userService.getUserById(owner);
        User exec = userService.getUserById(executer);
        Status stat = dictService.getStatusById(status);
        Task task = new Task(null, title, own, exec, description, stat);
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String viewTask(@PathVariable Long id, Model model) {
        model.addAttribute("task",  taskService.getTask(id));
        return "view";
    }

    @RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
    public String delTask(@PathVariable Long id) {
        taskService.delTask(id);
        return "redirect:/tasks";
    }
}
