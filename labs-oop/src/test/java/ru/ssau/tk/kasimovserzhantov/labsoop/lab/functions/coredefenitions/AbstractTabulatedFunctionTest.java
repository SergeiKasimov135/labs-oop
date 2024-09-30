package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractTabulatedFunctionTest {

    private final MockTabulatedFunction mockFunction = new MockTabulatedFunction(1., 3., 2., 4.);

    @Test
    public void interpolate_ReturnsValidResultAtBoundaries() {
        assertEquals(2., mockFunction.interpolate(1., 0), 0.001);
        assertEquals(4., mockFunction.interpolate(3., 1), 0.001);
    }

    @Test
    public void interpolate_ReturnsValidResultAtMiddle() {
        assertEquals(3., mockFunction.interpolate(2., 0), 0.001);
    }

    @Test
    public void interpolate_ReturnsValidResultOutsideBoundaries() {
        // Тестируем интерполяцию вне границ (должно возвращать y0 или y1)
        assertEquals(1.5, mockFunction.interpolate(0.5, 0), 0.001);
        assertEquals(4.5, mockFunction.interpolate(3.5, 1), 0.001);
    }

    @Test
    public void apply_ReturnsValidResultAtExactValues() {
        // Тестируем apply для точных значений внутри границ
        assertEquals(2., mockFunction.apply(1.), 0.001);
        assertEquals(4., mockFunction.apply(3.), 0.001);
    }

    @Test
    public void apply_ReturnsValidResultAtInterpolatedValues() {
        // Тестируем apply для интерполированных значений внутри границ
        assertEquals(3., mockFunction.apply(2.), 0.001);
        assertEquals(2.5, mockFunction.apply(1.5), 0.001);
        assertEquals(3.5, mockFunction.apply(2.5), 0.001);
    }

    @Test
    public void apply_ReturnsValidResultOutsideBoundaries() {
        // Тестируем apply для значений вне границ
        assertEquals(2., mockFunction.apply(0.), 0.001);
        assertEquals(4., mockFunction.apply(4.), 0.001);
    }
}