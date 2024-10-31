package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.PointDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;

import static org.junit.jupiter.api.Assertions.*;

class PointMapperTest {

    @Test
    void testPointEntityToDTO() {
        PointEntity entity = new PointEntity(1, null, 1.0, 2.0);
        PointDTO dto = PointMapper.pointEntityToDTO(entity);

        assertNotNull(dto);
        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getXValue(), dto.getXValue());

        dto = null;
        assertNull(dto);
    }

    @Test
    void testPointDTOToPointEntity() {
        PointDTO dto = new PointDTO(1, 1, 1.0, 2.0);
        PointEntity entity = PointMapper.pointDTOToPointEntity(dto);

        assertNotNull(entity);
        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getXValue(), entity.getXValue());

        entity = null;
        assertNull(entity);
    }
}
