package com.geekbrains.gwt.common;

public class TaskDto {
    private Long id;
    private String title;
    private String description;
    private Long statusId;
    private String status;
    private Long ownerId;
    private String owner;
    private Long executerId;
    private String executer;

    public TaskDto() {
    }

    public TaskDto(Long id, String title, String description, Long statusId, String status, Long ownerId, String owner, Long executerId, String executer) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.statusId = statusId;
        this.status = status;
        this.ownerId = ownerId;
        this.owner = owner;
        this.executerId = executerId;
        this.executer = executer;
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

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Long getExecuterId() {
        return executerId;
    }

    public void setExecuterId(Long executerId) {
        this.executerId = executerId;
    }

    public String getExecuter() {
        return executer;
    }

    public void setExecuter(String executer) {
        this.executer = executer;
    }
}
