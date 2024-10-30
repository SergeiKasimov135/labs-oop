package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;

public class MathFunctionMapper {

    public static MathFunctionDTO functionEntityToDTO(MathFunctionEntity entity) {
        if (entity == null) {
            return null;
        }

        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setId(entity.getId());
        dto.setFunctionType(entity.getFunctionType());
        dto.setCount(entity.getCount());
        dto.setXFrom(entity.getXFrom() != null ? entity.getXFrom() : 0.0);
        dto.setXTo(entity.getXTo() != null ? entity.getXTo() : 0.0);

        return dto;
    }

    public static MathFunctionEntity functionDTOToFunctionEntity(MathFunctionDTO dto) {
        if (dto == null) {
            return null;
        }

        MathFunctionEntity entity = new MathFunctionEntity();
        entity.setId(dto.getId());
        entity.setFunctionType(dto.getFunctionType());
        entity.setCount(dto.getCount());
        entity.setXFrom(dto.getXFrom());
        entity.setXTo(dto.getXTo());

        return entity;
    }

}
