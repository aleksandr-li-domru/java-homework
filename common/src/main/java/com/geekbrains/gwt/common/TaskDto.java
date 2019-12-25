package com.geekbrains.gwt.common;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private UserDto owner;
    private UserDto executer;
    private StatusDto status;


    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, UserDto owner, UserDto executer, StatusDto status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.executer = executer;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDto getOwner() {
        return owner;
    }

    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    public UserDto getExecuter() {
        return executer;
    }

    public void setExecuter(UserDto executer) {
        this.executer = executer;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto status) {
        this.status = status;
    }
}
