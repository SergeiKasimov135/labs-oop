package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.abstractclasses.AbstractTabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AbstractTabulatedFunctionTest {

    private final MockTabulatedFunction mockFunction = new MockTabulatedFunction(1., 3., 2., 4.);

    @Test
    public void checkLengthIsTheSame_ThrowsDifferentLengthOfArraysException() {
        // given
        double[] xValues = {1, 2, 3.5};
        double[] yValues = {3, 4.5, 2.7, 9};

        double[] xValues1 = {1, 2, 3.5, 6};
        double[] yValues1 = {3, 4.5, 2.7};

        double[] xValues2 = {};
        double[] yValues2 = {3, 4.5, 2.7};

        double[] xValues3 = {1, 2, 3.5, 6};
        double[] yValues3 = {};

        // then
        assertThrows(DifferentLengthOfArraysException.class,
                () -> AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues));

        assertThrows(DifferentLengthOfArraysException.class,
                () -> AbstractTabulatedFunction.checkLengthIsTheSame(xValues1, yValues1));

        assertThrows(DifferentLengthOfArraysException.class,
                () -> AbstractTabulatedFunction.checkLengthIsTheSame(xValues2, yValues2));

        assertThrows(DifferentLengthOfArraysException.class,
                () -> AbstractTabulatedFunction.checkLengthIsTheSame(xValues3, yValues3));
    }

    @Test
    public void checkSorted_ThrowsArrayIsNotSortedException() {
        // given
        double[] xValues = {1, 52.52, 3.5};

        // then
        assertThrows(ArrayIsNotSortedException.class, () -> AbstractTabulatedFunction.checkSorted(xValues));
    }

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

class MockTabulatedFunction extends AbstractTabulatedFunction {

    private final double x0, x1;
    private double y0, y1;

    public MockTabulatedFunction(double x0, double x1, double y0, double y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

    private void isIndexValid(int index) {
        if (!(index == 0 || index == 1))
            throw new IndexOutOfBoundsException("Mock object has only two coords");
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        isIndexValid(index);
        return index == 0 ? x0 : x1;
    }

    @Override
    public double getY(int index) {
        isIndexValid(index);
        return index == 0 ? y0 : y1;
    }

    @Override
    public void setY(int index, double value) {
        isIndexValid(index);
        if (index == 0) {
            y0 = value;
        } else {
            y1 = value;
        }
    }

    @Override
    public int indexOfX(double x) {
        if (x == x0) return 0;
        if (x == x1) return 1;
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        if (y == y0) return 0;
        if (y == y1) return 1;
        return -1;
    }

    @Override
    public double leftBound() {
        return x0;
    }

    @Override
    public double rightBound() {
        return x1;
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < 2;
            }

            @Override
            public Point next() {
                if (!hasNext()) {
                    throw new java.util.NoSuchElementException();
                }
                return new Point(i == 0 ? x0 : x1, i == 0 ? y0 : y1);
            }
        };
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < x0) return 0;
        if (x >= x1) return 1;
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return y0;
    }

    @Override
    protected double extrapolateRight(double x) {
        return y1;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, x0, x1, y0, y1);
    }

}
