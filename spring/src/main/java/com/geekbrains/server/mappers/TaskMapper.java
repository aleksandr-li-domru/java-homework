package com.geekbrains.server.mappers;

import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.server.entities.Task;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(uses = { UserMapper.class, StatusMapper.class })
public interface TaskMapper {
    TaskMapper MAPPER = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "executer", target = "executer")
    @Mapping(source = "status", target = "status")
    Task toTask(TaskDto itemDto);

    @InheritInverseConfiguration
    TaskDto fromTask(Task task);

    List<Task> toTaskList(List<TaskDto> taskDtos);

    List<TaskDto> fromTaskList(List<Task> tasks);
}
