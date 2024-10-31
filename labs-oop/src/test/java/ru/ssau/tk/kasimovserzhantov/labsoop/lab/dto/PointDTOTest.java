package ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointDTOTest {

    @Test
    public void testPointDTO() {
        PointDTO dto = new PointDTO(1, 1, 5.0, 10.0);

        assertEquals(1, dto.getId());
        assertEquals(1, dto.getFunctionId());
        assertEquals(5.0, dto.getXValue());
        assertEquals(10.0, dto.getYValue());
    }

}