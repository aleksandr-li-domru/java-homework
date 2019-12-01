package com.geekbrains.traning.tasks;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "id_executer")
    private User executer;

    @Column(name = "descriprion")
    private String descriprion;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    public Task() {

    }

    public Task(Long id, String title, User owner, User executer, String descriprion, Status status) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.executer = executer;
        this.descriprion = descriprion;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getOwner() {
        return owner;
    }

    public User getExecuter() {
        return executer;
    }

    public String getDescriprion() {
        return descriprion;
    }

    public Status getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExecuter(User executer) {
        this.executer = executer;
    }

    public void setDescriprion(String descriprion) {
        this.descriprion = descriprion;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Task %d: [%s, %s, %s, %s, %s]", id, title, owner, executer, descriprion, status);
    }
}
