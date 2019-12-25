package com.geekbrains.server.entities;

import com.geekbrains.gwt.common.TaskDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
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

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "id_status")
    private Status status;

    public Task(Long id, String title, User owner, User executer, String descriprion, Status status) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.executer = executer;
        this.description = descriprion;
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("Task %d: [%s, %s, %s, %s, %s]", id, title, owner, executer, description, status);
    }
}
