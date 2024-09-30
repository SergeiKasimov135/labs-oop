package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;

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
    public void testFloorIndexOfX_ReturnsCorrectFloorIndex() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertEquals(1, function.floorIndexOfX(2));
        assertEquals(1, function.floorIndexOfX(2.5));
    }

    @Test
    public void testInterpolate_ReturnsCorrectInterpolatedValue() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // when
        double interpolatedValue = function.interpolate(2.5, function.floorIndexOfX(2.5));

        // then
        assertEquals(5.5, interpolatedValue, 0.0001);
    }

    @Test
    public void testExtrapolateLeftAndRight_ReturnsCorrectExtrapolatedValues() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertEquals(3, function.extrapolateLeft(0), 0.0001);
        assertEquals(7, function.extrapolateRight(4), 0.0001);
    }

    @Test
    public void testInvalidConstructorArguments_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(new double[]{}, new double[]{}));

        assertThrows(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(new double[]{1}, new double[]{1, 2}));

        assertThrows(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(new double[]{2, 1}, new double[]{1, 2}));

        assertThrows(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(null, 0, 1, 2));

        assertThrows(IllegalArgumentException.class,
                () -> new ArrayTabulatedFunction(null, 0, 1, 0));
    }

    @Test
    public void testInvalidInsertArguments_ThrowsIllegalArgumentException() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertThrows(IllegalArgumentException.class, () -> function.insert(Double.NaN, 5));
    }

    @Test
    public void testInvalidSetYArguments_ThrowsIndexOutOfBoundsException() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertThrows(IndexOutOfBoundsException.class, () -> function.setY(-1, 10));
    }


    @Test
    public void testRemove_InvalidIndex_ThrowsIndexOutOfBoundsException() {
        // given
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};

        // when
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        // then
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> function.remove(3));
    }

}
