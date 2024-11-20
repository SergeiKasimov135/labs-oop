package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.PointDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;

@Mapper(componentModel = "spring")
public interface PointMapper {

    @Mapping(target = "functionEntity", ignore = true)
    PointEntity toEntity(PointDTO dto);

    @Mapping(source = "functionEntity.id", target = "functionId")
    PointDTO toDTO(PointEntity entity);

    default PointEntity toEntityWithFunction(PointDTO dto, MathFunctionEntity functionEntity) {
        PointEntity entity = toEntity(dto);
        entity.setFunctionEntity(functionEntity);
        return entity;
    }

}
