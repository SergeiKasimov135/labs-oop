package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class UnmodifiableTabulatedFunctionTest {

    @Test
    void testGetCount() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(originalFunction.getCount(), unmodifiableFunction.getCount());
    }

    @Test
    void testGetX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(originalFunction.getX(1), unmodifiableFunction.getX(1));
    }

    @Test
    void testGetY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(originalFunction.getY(1), unmodifiableFunction.getY(1));
    }

    @Test
    void testSetY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertThrows(UnsupportedOperationException.class, () -> unmodifiableFunction.setY(1, 10));
    }

    @Test
    void testIndexOfX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(1, unmodifiableFunction.indexOfX(2));
        assertEquals(-1, unmodifiableFunction.indexOfX(4));
    }

    @Test
    void testIndexOfY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(1, unmodifiableFunction.indexOfY(5));
        assertEquals(-1, unmodifiableFunction.indexOfY(32));
    }

    @Test
    void testLeftBound() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(originalFunction.leftBound(), unmodifiableFunction.leftBound());
    }

    @Test
    void testRightBound() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(originalFunction.rightBound(), unmodifiableFunction.rightBound());
    }

    @Test
    void testApply() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        assertEquals(5, unmodifiableFunction.apply(2), 0.0001);
    }

    @Test
    void testIterator() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);

        Iterator<Point> iterator = unmodifiableFunction.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValues[index], point.getX(), 0.0001);
            assertEquals(yValues[index], point.getY(), 0.0001);
            index++;
        }
        assertEquals(xValues.length, index);
    }

    @Test
    void testSetY_StrictWrappedInUnmodifiable() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(strictFunction);

        assertThrows(UnsupportedOperationException.class, () -> unmodifiableFunction.setY(1, 10));
    }

    @Test
    void testApply_StrictWrappedInUnmodifiable() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(strictFunction);

        assertEquals(5, unmodifiableFunction.apply(2), 0.0001);
        assertThrows(UnsupportedOperationException.class, () -> unmodifiableFunction.apply(4));
    }

    @Test
    void testIndexOfX_StrictWrappedInUnmodifiable() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(strictFunction);

        assertEquals(1, unmodifiableFunction.indexOfX(2));
        assertEquals(-1, unmodifiableFunction.indexOfX(4));
    }

    @Test
    void testIterator_StrictWrappedInUnmodifiable() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(strictFunction);

        Iterator<Point> iterator = unmodifiableFunction.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(xValues[index], point.getX(), 0.0001);
            assertEquals(yValues[index], point.getY(), 0.0001);
            index++;
        }
        assertEquals(xValues.length, index);
    }

}
