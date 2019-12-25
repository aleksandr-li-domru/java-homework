package com.geekbrains.server.mappers;

import com.geekbrains.gwt.common.StatusDto;
import com.geekbrains.server.entities.Status;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StatusMapper {
    StatusMapper MAPPER = Mappers.getMapper(StatusMapper.class);

    Status toStatus(StatusDto statusDto);

    @InheritInverseConfiguration
    StatusDto fromStatus(Status status);

    List<Status> toStatusList(List<StatusDto> statusDtos);

    List<StatusDto> fromStatusList(List<Status> statuses);
}
