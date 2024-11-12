package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.PointDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.service.PointService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PointRestControllerTest {

    @Mock
    private PointService pointService;

    @InjectMocks
    private PointRestController controller;

    private PointDTO pointDTO;

    @BeforeEach
    void setUp() {
        pointDTO = new PointDTO(1, 1, 5.0, 10.0);
    }

    @Test
    void findAllPoints_PointsExist_ReturnsOk() {
        // given
        List<PointDTO> points = List.of(pointDTO);
        when(pointService.findAllPoints(1)).thenReturn(points);

        // when
        ResponseEntity<List<PointDTO>> result = controller.findAllPoints(1);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(points, result.getBody());
        verify(pointService).findAllPoints(1);
    }

    @Test
    void findAllPoints_PointsNotFound_ReturnsNotFound() {
        // given
        when(pointService.findAllPoints(99)).thenReturn(null);

        // when
        ResponseEntity<List<PointDTO>> result = controller.findAllPoints(99);

        // then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(pointService).findAllPoints(99);
    }

    @Test
    void create_CreatesPoint_ReturnsOk() {
        // given
        when(pointService.create(pointDTO)).thenReturn(pointDTO);

        // when
        ResponseEntity<PointDTO> result = controller.create(pointDTO);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pointDTO, result.getBody());
        verify(pointService).create(pointDTO);
    }

    @Test
    void read_PointExists_ReturnsOk() {
        // given
        when(pointService.read(1)).thenReturn(pointDTO);

        // when
        ResponseEntity<PointDTO> result = controller.read(1);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pointDTO, result.getBody());
        verify(pointService).read(1);
    }

    @Test
    void read_PointNotFound_ReturnsNotFound() {
        // given
        when(pointService.read(99)).thenReturn(null);

        // when
        ResponseEntity<PointDTO> result = controller.read(99);

        // then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertNull(result.getBody());
        verify(pointService).read(99);
    }

    @Test
    void update_ValidPoint_ReturnsOk() {
        // given
        PointDTO updatedPoint = new PointDTO(1, 1, 5.0, 15.0);
        when(pointService.update(updatedPoint)).thenReturn(updatedPoint);

        // when
        ResponseEntity<PointDTO> result = controller.update(updatedPoint, 1);

        // then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedPoint, result.getBody());
        verify(pointService).update(updatedPoint);
    }

    @Test
    void delete_DeletesPoint_ReturnsNoContent() {
        // given

        // when
        ResponseEntity<Void> result = controller.delete(1);

        // then
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(pointService).delete(1);
    }
}
