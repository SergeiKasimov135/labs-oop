package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.InterpolationException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTabulatedFunctionTest {

    @Test
    public void testConstructorWithArrays_ReturnsCorrectCountAndValues() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertEquals(3, function.getCount());
        assertEquals(1, function.getX(0), 0.0001);
        assertEquals(4, function.getY(0), 0.0001);
        assertEquals(3, function.getX(2), 0.0001);
        assertEquals(6, function.getY(2), 0.0001);
    }

    @Test
    public void testConstructorWithArrays_ThrowsIllegalArgumentException() {
        // given
        double[] xValues = {1};
        double[] yValues = {4};

        // then
        assertThrows(IllegalArgumentException.class, () -> new ArrayTabulatedFunction(xValues, yValues));
    }

    @Test
    public void testConstructorWithMathFunction_ReturnsCorrectCountAndValues() {
        // given
        MathFunction source = x -> x * x;

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 0, 2, 3);

        // then
        assertEquals(3, function.getCount());
        assertEquals(0, function.getX(0), 0.0001);
        assertEquals(0, function.getY(0), 0.0001);
        assertEquals(2, function.getX(2), 0.0001);
        assertEquals(4, function.getY(2), 0.0001);
    }

    @Test
    public void testConstructorWithMathFunction_SwappedBounds_ReturnsCorrectCountAndValues() {
        // given
        MathFunction source = x -> x * x;
        double xFrom = 2;
        double xTo = 0;
        int count = 3;

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, xFrom, xTo, count);

        // then
        assertEquals(3, function.getCount());
        assertEquals(0, function.getX(0), 0.0001);
        assertEquals(0, function.getY(0), 0.0001);
        assertEquals(2, function.getX(2), 0.0001);
        assertEquals(4, function.getY(2), 0.0001);
    }

    @Test
    public void testConstructorWithMathFunction_SameBounds_ReturnsCorrectCountAndValues() {
        // given
        MathFunction source = x -> x * x;
        double xFrom = 1;
        double xTo = 1;
        int count = 3;

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, xFrom, xTo, count);

        // then
        assertEquals(3, function.getCount());
        assertEquals(1, function.getX(0), 0.0001);
        assertEquals(1, function.getY(0), 0.0001);
        assertEquals(1, function.getX(2), 0.0001);
        assertEquals(1, function.getY(2), 0.0001);
    }

    @Test
    public void testInsert_ReturnsSameCountAndUpdatedValues() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 7};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.insert(2, 6);

        // then
        assertEquals(3, function.getCount());
        assertEquals(2, function.getX(1), 0.0001);
        assertEquals(6, function.getY(1), 0.0001);
    }

    @Test
    public void testInsert_ReturnsUpdatedCountAndValues() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.insert(1.5, 5.5);

        // then
        assertEquals(4, function.getCount());
        assertEquals(1.5, function.getX(1), 0.0001);
        assertEquals(5.5, function.getY(1), 0.0001);
    }

    @Test
    public void testRemove_ReturnsUpdatedCountAndValues() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.remove(1);

        // then
        assertEquals(2, function.getCount());
        assertEquals(1, function.getX(0), 0.0001);
        assertEquals(4, function.getY(0), 0.0001);
        assertEquals(3, function.getX(1), 0.0001);
        assertEquals(6, function.getY(1), 0.0001);
    }

    @Test
    public void testRemove_FromBeginning_ReturnsUpdatedCountAndValues() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.remove(0);

        // then
        assertEquals(2, function.getCount());
        assertEquals(2, function.getX(0), 0.0001);
        assertEquals(5, function.getY(0), 0.0001);
        assertEquals(3, function.getX(1), 0.0001);
        assertEquals(6, function.getY(1), 0.0001);
    }

    @Test
    public void testRemove_FromEnd_ReturnsUpdatedCountAndValues() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.remove(2);

        // then
        assertEquals(2, function.getCount());
        assertEquals(1, function.getX(0), 0.0001);
        assertEquals(4, function.getY(0), 0.0001);
        assertEquals(2, function.getX(1), 0.0001);
        assertEquals(5, function.getY(1), 0.0001);
    }

    @Test
    public void testSetY_ReturnsUpdatedYValue() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.setY(1, 10);

        // then
        assertEquals(10, function.getY(1), 0.0001);
    }

    @Test
    public void testIndexOfX_ReturnsCorrectIndex() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertEquals(1, function.indexOfX(2));
        assertEquals(-1, function.indexOfX(4));
    }

    @Test
    public void testIndexOfY_ReturnsCorrectIndex() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertEquals(1, function.indexOfY(5));
        assertEquals(-1, function.indexOfY(32));
    }

    @Test
    public void testLeftAndRightBound_ReturnsCorrectBounds() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertEquals(1, function.leftBound(), 0.0001);
        assertEquals(3, function.rightBound(), 0.0001);
    }

    @Test
    public void testIteratorUsingWhileLoop() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        Iterator<Point> iterator = function.iterator();

        // when
        double[] expectedX = {1, 2, 3};
        double[] expectedY = {4, 5, 6};
        int index = 0;

        // then
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(expectedX[index], point.getX(), 0.0001);
            assertEquals(expectedY[index], point.getY(), 0.0001);
            index++;
        }
        assertEquals(3, index);
    }

    @Test
    public void testIteratorUsingForEachLoop() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // when
        double[] expectedX = {1, 2, 3};
        double[] expectedY = {4, 5, 6};
        int index = 0;

        // then
        for (Point point : function) {
            assertEquals(expectedX[index], point.getX(), 0.0001);
            assertEquals(expectedY[index], point.getY(), 0.0001);
            index++;
        }
        assertEquals(3, index);
    }

    @Test
    public void testConstructorWithMathFunction_ReturnsCorrectValues() {
        MathFunction source = x -> x * x;
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(source, 0, 2, 3);
        assertEquals(3, function.getCount());
        assertEquals(0, function.getX(0), 0.0001);
        assertEquals(0, function.getY(0), 0.0001);
    }

    @Test
    public void testInsert_UpdatesValueIfExists() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.insert(2, 10);
        assertEquals(10, function.getY(1), 0.0001);
    }

    @Test
    public void testInsert_AddsValueIfNotExists() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.insert(1.5, 5.5);
        assertEquals(4, function.getCount());
        assertEquals(1.5, function.getX(1), 0.0001);
        assertEquals(5.5, function.getY(1), 0.0001);
    }

    @Test
    public void testRemove_RemovesValue() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        function.remove(1);
        assertEquals(2, function.getCount());
        assertEquals(1, function.getX(0), 0.0001);
    }

    @Test
    public void testGetX_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.getX(3));
    }

    @Test
    public void testGetY_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.getY(3));
    }

    @Test
    public void testSetY_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.setY(3, 10));
    }

    @Test
    public void testIterator() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        Iterator<Point> iterator = function.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1, iterator.next().getX(), 0.0001);
        assertTrue(iterator.hasNext());
        assertEquals(2, iterator.next().getX(), 0.0001);
        assertTrue(iterator.hasNext());
        assertEquals(3, iterator.next().getX(), 0.0001);
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testInsert_ThrowsIllegalArgumentExceptionForNaN() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IllegalArgumentException.class, () -> function.insert(Double.NaN, 6));
        assertThrows(IllegalArgumentException.class, () -> function.insert(2, Double.NaN));
    }

    @Test
    public void testRemove_ThrowsIndexOutOfBoundsException() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(3));
    }

    @Test
    public void testFloorIndexOfX_HandlesEdgeCases() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        assertEquals(-1, function.floorIndexOfX(0)); // Before first
        assertEquals(0, function.floorIndexOfX(1)); // First
        assertEquals(0, function.floorIndexOfX(1.5)); // Between
        assertEquals(2, function.floorIndexOfX(3)); // Last
        assertEquals(2, function.floorIndexOfX(4)); // After last
    }

    @Test
    public void testExtrapolateLeft() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        assertEquals(3, function.extrapolateLeft(0), 0.0001);
    }

    @Test
    public void testExtrapolateRight() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        assertEquals(7, function.extrapolateRight(4), 0.0001);
    }

    @Test
    public void testInterpolate_ThrowsInterpolationException() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        assertThrows(InterpolationException.class, () -> function.interpolate(0, 0));
        assertThrows(InterpolationException.class, () -> function.interpolate(5, 1));
    }

    @Test
    public void testInterpolate_DoesNotThrowException() {
        double[] xValues = {1, 2, 3};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, new double[]{4, 5, 6});
        assertDoesNotThrow(() -> function.interpolate(1.5, 0));
    }
}