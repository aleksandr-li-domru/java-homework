package com.geekbrains.server.entities;

import com.geekbrains.gwt.common.StatusDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "statuses")
@Data
@NoArgsConstructor
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

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

    public StatusDto getDto() {
        return new StatusDto(id, name);
    }
}
