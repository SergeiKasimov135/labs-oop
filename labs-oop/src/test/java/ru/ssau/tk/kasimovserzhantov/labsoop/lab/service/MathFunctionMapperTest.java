package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionMapperTest {

    @Test
    void testFunctionEntityToDTO() {
        MathFunctionEntity entity = new MathFunctionEntity(1, "linear", 10, 0.0, 10.0, null);
        MathFunctionDTO dto = MathFunctionMapper.functionEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getFunctionType(), dto.getFunctionType());

        dto = null;
        assertNull(dto);
    }

    @Test
    void testFunctionDTOToFunctionEntity() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "linear", 10, 0.0, 10.0, null);
        MathFunctionEntity entity = MathFunctionMapper.functionDTOToFunctionEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getFunctionType(), entity.getFunctionType());

        entity = null;
        assertNull(entity);
    }

}
