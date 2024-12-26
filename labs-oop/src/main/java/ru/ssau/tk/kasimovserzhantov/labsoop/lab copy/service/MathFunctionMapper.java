package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import org.mapstruct.*;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.PointDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;

import java.util.ArrayList;

@Mapper(componentModel = "spring", uses = {PointMapper.class})
public interface MathFunctionMapper {

    @Mapping(target = "points")
    MathFunctionEntity toEntity(MathFunctionDTO dto);

    MathFunctionDTO toDTO(MathFunctionEntity entity);

    @AfterMapping
    default void mapPoints(@MappingTarget MathFunctionEntity entity, MathFunctionDTO dto,
                           @Context PointMapper pointMapper) {
        if (dto.getPoints() != null) {
            if (entity.getPoints() == null) {
                entity.setPoints(new ArrayList<>());
            }
            entity.getPoints().addAll(dto.getPoints().stream()
                    .map(pointDTO -> pointMapper.toEntityWithFunction(pointDTO, entity))
                    .toList());
        }
    }

    default PointEntity toEntityWithFunction(PointDTO pointDTO, MathFunctionEntity functionEntity) {
        PointEntity pointEntity = new PointEntity();
        pointEntity.setXValue(pointDTO.getXValue());
        pointEntity.setYValue(pointDTO.getYValue());
        pointEntity.setFunctionEntity(functionEntity);
        return pointEntity;
    }

}
