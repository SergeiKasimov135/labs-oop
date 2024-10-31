package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.MathFunctionRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MathFunctionServiceTest {

    @Mock
    private MathFunctionRepository mathFunctionRepository;

    @InjectMocks
    private MathFunctionService mathFunctionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetByFunctionType() {
        MathFunctionEntity entity = new MathFunctionEntity(1, "linear", 10, 0.0, 10.0, null);
        when(mathFunctionRepository.findByFunctionType("linear")).thenReturn(Arrays.asList(entity));

        List<MathFunctionDTO> dtos = mathFunctionService.findAllFunctions("linear");

        assertNotNull(dtos);
        assertEquals(1, dtos.size());
        assertEquals(entity.getFunctionType(), dtos.get(0).getFunctionType());
    }

    @Test
    void testCreate() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "linear", 10, 0.0, 10.0, null);
        MathFunctionEntity entity = new MathFunctionEntity(1, "linear", 10, 0.0, 10.0, null);
        when(mathFunctionRepository.save(any())).thenReturn(entity);

        MathFunctionDTO createdDto = mathFunctionService.create(dto);

        assertNotNull(createdDto);
        assertEquals(dto.getFunctionType(), createdDto.getFunctionType());
        verify(mathFunctionRepository).save(any());
    }

    @Test
    void testRead() {
        MathFunctionEntity entity = new MathFunctionEntity(1, "linear", 10, 0.0, 10.0, null);
        when(mathFunctionRepository.findById(1)).thenReturn(Optional.of(entity));

        MathFunctionDTO dto = mathFunctionService.read(1);

        assertNotNull(dto);
        assertEquals(entity.getFunctionType(), dto.getFunctionType());
    }

    @Test
    void testUpdate() {
        MathFunctionDTO dto = new MathFunctionDTO(1, "linear", 10, 0.0, 10.0, null);
        MathFunctionEntity entity = new MathFunctionEntity(1, "linear", 10, 0.0, 10.0, null);
        when(mathFunctionRepository.save(any())).thenReturn(entity);

        MathFunctionDTO updatedDto = mathFunctionService.update(dto);

        assertNotNull(updatedDto);
        assertEquals(dto.getFunctionType(), updatedDto.getFunctionType());
        verify(mathFunctionRepository).save(any());
    }

    @Test
    void testDelete() {
        mathFunctionService.delete(1);
        verify(mathFunctionRepository).deleteById(1);
    }
}
