package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class StrictTabulatedFunctionTest {

    @Test
    void testGetCount() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(originalFunction.getCount(), strictFunction.getCount());
    }

    @Test
    void testGetX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(originalFunction.getX(1), strictFunction.getX(1));
    }

    @Test
    void testGetY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(originalFunction.getY(1), strictFunction.getY(1));
    }

    @Test
    void testSetY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        strictFunction.setY(1, 10);
        assertEquals(10, strictFunction.getY(1));
    }

    @Test
    void testIndexOfX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(1, strictFunction.indexOfX(2));
        assertEquals(-1, strictFunction.indexOfX(4));
    }

    @Test
    void testIndexOfY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(1, strictFunction.indexOfY(5));
        assertEquals(-1, strictFunction.indexOfY(32));
    }

    @Test
    void testLeftBound() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(originalFunction.leftBound(), strictFunction.leftBound());
    }

    @Test
    void testRightBound() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(originalFunction.rightBound(), strictFunction.rightBound());
    }

    @Test
    void testApplyWithExistingX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertEquals(5, strictFunction.apply(2), 0.0001);
    }

    @Test
    void testApplyWithNonExistingX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        assertThrows(UnsupportedOperationException.class, () -> strictFunction.apply(4));
    }

    @Test
    void testIterator() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(originalFunction);

        Iterator<Point> iterator = strictFunction.iterator();
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
    void testSetY_UnmodifiableWrappedInStrict() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(unmodifiableFunction);

        assertThrows(UnsupportedOperationException.class, () -> strictFunction.setY(1, 10));
    }

    @Test
    void testApply_UnmodifiableWrappedInStrict() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(unmodifiableFunction);

        assertEquals(5, strictFunction.apply(2), 0.0001);
        assertThrows(UnsupportedOperationException.class, () -> strictFunction.apply(4));
    }

    @Test
    void testIndexOfX_UnmodifiableWrappedInStrict() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(unmodifiableFunction);

        assertEquals(1, strictFunction.indexOfX(2));
        assertEquals(-1, strictFunction.indexOfX(4));
    }

    @Test
    void testIterator_UnmodifiableWrappedInStrict() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        TabulatedFunction originalFunction = new ArrayTabulatedFunction(xValues, yValues);
        UnmodifiableTabulatedFunction unmodifiableFunction = new UnmodifiableTabulatedFunction(originalFunction);
        StrictTabulatedFunction strictFunction = new StrictTabulatedFunction(unmodifiableFunction);

        Iterator<Point> iterator = strictFunction.iterator();
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
