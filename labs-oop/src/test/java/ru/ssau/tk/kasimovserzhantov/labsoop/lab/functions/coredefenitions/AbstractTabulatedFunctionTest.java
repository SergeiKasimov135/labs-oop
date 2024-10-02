package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractTabulatedFunctionTest {

    private final MockTabulatedFunction mockFunction = new MockTabulatedFunction(1., 3., 2., 4.);

    @Test
    public void isIndexValid_ThrowsIndexOutOfBoundsException() {
        // given
        int index = 3;

        // then
        assertThrows(IndexOutOfBoundsException.class, () -> mockFunction.getX(index));
    }

    @Test
    public void getCount_ReturnsCount() {
        assertEquals(2, mockFunction.getCount());
    }

    @Test
    public void getX_ReturnsValidResult() {
        assertEquals(1, mockFunction.getX(0));
        assertEquals(3, mockFunction.getX(1));
    }

    @Test
    public void getY_ReturnsValidResult() {
        assertEquals(2, mockFunction.getY(0));
        assertEquals(4, mockFunction.getY(1));
    }

    @Test
    public void setY_ValidIndex_SetsValueCorrectly() {
        mockFunction.setY(0, 5.0);
        assertEquals(5.0, mockFunction.getY(0));

        mockFunction.setY(1, 6.0);
        assertEquals(6.0, mockFunction.getY(1));
    }

    @Test
    public void indexOfY_ReturnsCorrectIndex() {
        assertEquals(0, mockFunction.indexOfY(2.0));

        assertEquals(1, mockFunction.indexOfY(4.0));

        assertEquals(-1, mockFunction.indexOfY(5.0));
        assertEquals(-1, mockFunction.indexOfY(1.0));
        assertEquals(-1, mockFunction.indexOfY(3.0));
    }

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
        assertEquals(1.5, mockFunction.interpolate(0.5, 0), 0.001);
        assertEquals(4.5, mockFunction.interpolate(3.5, 1), 0.001);
    }

    @Test
    public void apply_ReturnsValidResultAtExactValues() {
        assertEquals(2., mockFunction.apply(1.), 0.001);
        assertEquals(4., mockFunction.apply(3.), 0.001);
    }

    @Test
    public void apply_ReturnsValidResultAtInterpolatedValues() {
        assertEquals(3., mockFunction.apply(2.), 0.001);
        assertEquals(2.5, mockFunction.apply(1.5), 0.001);
        assertEquals(3.5, mockFunction.apply(2.5), 0.001);
    }

    @Test
    public void apply_ReturnsValidResultOutsideBoundaries() {
        assertEquals(2., mockFunction.apply(0.), 0.001);
        assertEquals(4., mockFunction.apply(4.), 0.001);
    }
}
