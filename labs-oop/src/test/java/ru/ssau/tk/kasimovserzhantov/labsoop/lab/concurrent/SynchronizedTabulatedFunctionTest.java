package ru.ssau.tk.kasimovserzhantov.labsoop.lab.concurrent;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;

import java.util.Iterator;

public class SynchronizedTabulatedFunctionTest {

    private MockTabulatedFunction mockFunction;
    private SynchronizedTabulatedFunction synchronizedFunction;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        mockFunction = new MockTabulatedFunction(xValues, yValues);
        synchronizedFunction = new SynchronizedTabulatedFunction(mockFunction);
    }

    @Test
    public void testGetCount() {
        assertEquals(3, synchronizedFunction.getCount());
    }

    @Test
    public void testGetX() {
        assertEquals(1.0, synchronizedFunction.getX(0));
        assertEquals(2.0, synchronizedFunction.getX(1));
        assertEquals(3.0, synchronizedFunction.getX(2));
    }

    @Test
    public void testGetY() {
        assertEquals(2.0, synchronizedFunction.getY(0));
        assertEquals(4.0, synchronizedFunction.getY(1));
        assertEquals(6.0, synchronizedFunction.getY(2));
    }

    @Test
    public void testSetY() {
        synchronizedFunction.setY(1, 5.0);
        assertEquals(5.0, synchronizedFunction.getY(1));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(1, synchronizedFunction.indexOfX(2.0));
        assertEquals(-1, synchronizedFunction.indexOfX(4.0));
    }

    @Test
    public void testIndexOfY() {
        assertEquals(0, synchronizedFunction.indexOfY(2.0));
        assertEquals(-1, synchronizedFunction.indexOfY(5.0));
    }

    @Test
    public void testLeftBound() {
        assertEquals(1.0, synchronizedFunction.leftBound());
    }

    @Test
    public void testRightBound() {
        assertEquals(3.0, synchronizedFunction.rightBound());
    }

    @Test
    public void testApply() {
        assertEquals(4.0, synchronizedFunction.apply(2.0));
        assertEquals(Double.NaN, synchronizedFunction.apply(4.0));
    }

    @Test
    public void testIterator() {
        Iterator<Point> iterator = synchronizedFunction.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(1.0, iterator.next().getX());
        assertTrue(iterator.hasNext());
        assertEquals(2.0, iterator.next().getX());
        assertTrue(iterator.hasNext());
        assertEquals(3.0, iterator.next().getX());
        assertFalse(iterator.hasNext());
    }
}

