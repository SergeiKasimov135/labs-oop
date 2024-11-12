package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.service.MathFunctionService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MathFunctionRestControllerTest {

    @Mock
    private MathFunctionService mathFunctionService;

    @InjectMocks
    private MathFunctionRestController controller;

    private MathFunctionDTO functionDTO;

    @BeforeEach
    void setUp() {
        functionDTO = new MathFunctionDTO(1, "polynomial", 2, -10.0, 10.0, List.of());
    }

    @Test
    void findAllFunctions_FunctionsExist_ReturnsOk() {
        // given
        List<MathFunctionDTO> functions = List.of(functionDTO);
        when(mathFunctionService.findAllFunctions("polynomial")).thenReturn(functions);

        // when
        ResponseEntity<List<MathFunctionDTO>> result = controller.findAllFunctions("polynomial");

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(functions, result.getBody());
        verify(mathFunctionService).findAllFunctions("polynomial");
    }

    @Test
    void findAllFunctions_FunctionsNotFound_ReturnsNotFound() {
        // given
        when(mathFunctionService.findAllFunctions("trigonometric")).thenReturn(null);

        // when
        ResponseEntity<List<MathFunctionDTO>> result = controller.findAllFunctions("trigonometric");

        // then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(mathFunctionService).findAllFunctions("trigonometric");
    }

    @Test
    void create_CreatesFunction_ReturnsOk() {
        // given
        when(mathFunctionService.create(functionDTO)).thenReturn(functionDTO);

        // when
        ResponseEntity<MathFunctionDTO> result = controller.create(functionDTO);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(functionDTO, result.getBody());
        verify(mathFunctionService).create(functionDTO);
    }

    @Test
    void read_FunctionExists_ReturnsOk() {
        // given
        when(mathFunctionService.read(1)).thenReturn(functionDTO);

        // when
        ResponseEntity<MathFunctionDTO> result = controller.read(1);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(functionDTO, result.getBody());
        verify(mathFunctionService).read(1);
    }

    @Test
    void read_FunctionNotFound_ReturnsNotFound() {
        // given
        when(mathFunctionService.read(99)).thenReturn(null);

        // when
        ResponseEntity<MathFunctionDTO> result = controller.read(99);

        // then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(mathFunctionService).read(99);
    }

    @Test
    void update_ValidFunction_ReturnsOk() {
        // given
        MathFunctionDTO updatedFunction = new MathFunctionDTO(1, "polynomial", 3, -10.0, 20.0, List.of());
        when(mathFunctionService.update(updatedFunction)).thenReturn(updatedFunction);

        // when
        ResponseEntity<MathFunctionDTO> result = controller.update(updatedFunction, 1);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedFunction, result.getBody());
        verify(mathFunctionService).update(updatedFunction);
    }

    @Test
    void delete_DeletesFunction_ReturnsNoContent() {
        // given

        // when
        ResponseEntity<Void> result = controller.delete(1);

        // then
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(mathFunctionService).delete(1);
    }
}
