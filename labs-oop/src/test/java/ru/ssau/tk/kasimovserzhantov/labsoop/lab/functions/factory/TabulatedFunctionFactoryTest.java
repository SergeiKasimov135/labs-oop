package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabulatedFunctionFactoryTest {

    private final TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();

    private static class ArrayFactory implements TabulatedFunctionFactory {
        @Override
        public TabulatedFunction create(double[] xValues, double[] yValues) {
            return new ArrayTabulatedFunction(xValues, yValues);
        }
    }

    private static class LinkedListFactory implements TabulatedFunctionFactory {
        @Override
        public TabulatedFunction create(double[] xValues, double[] yValues) {
            return new LinkedListTabulatedFunction(xValues, yValues);
        }
    }

    @Test
    public void testCreateStrictWithArrayFactory() {
        TabulatedFunctionFactory factory = new ArrayFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 3.0, 5.0};

        TabulatedFunction strictFunction = factory.createStrict(xValues, yValues);

        assertEquals(3, strictFunction.getCount());
        assertEquals(3.0, strictFunction.getY(1));
    }

    @Test
    public void testCreateStrictWithLinkedListFactory() {
        TabulatedFunctionFactory factory = new LinkedListFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 3.0, 5.0};

        TabulatedFunction strictFunction = factory.createStrict(xValues, yValues);

        assertEquals(3, strictFunction.getCount());
        assertEquals(5.0, strictFunction.getY(2));
    }

    @Test
    public void testStrictFunctionDoesNotAllowInterpolation() {
        TabulatedFunctionFactory factory = new ArrayFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 3.0, 5.0};

        TabulatedFunction strictFunction = factory.createStrict(xValues, yValues);

        assertThrows(UnsupportedOperationException.class, () -> strictFunction.apply(1.5));
    }

    @Test
    public void testCreateStrictWithSingleElementArrays() {
        TabulatedFunctionFactory factory = new ArrayFactory();
        double[] xValues = {1.0};
        double[] yValues = {2.0}; 

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createStrict(xValues, yValues);
        });
    }

    @Test
    public void testCreateStrictWithEmptyArrays() {
        TabulatedFunctionFactory factory = new ArrayFactory();
        double[] xValues = {};
        double[] yValues = {};

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createStrict(xValues, yValues);
        });
    }
    @Test
    public void testCreateUnmodifiable() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 3.0, 5.0};

        TabulatedFunction unmodifiableFunction = factory.createUnmodifiable(xValues, yValues);

        assertThrows(UnsupportedOperationException.class, () -> {
            unmodifiableFunction.setY(1, 10.0); 
        });

        assertEquals(2.0, unmodifiableFunction.getY(0));
        assertEquals(3.0, unmodifiableFunction.getY(1));
        assertEquals(5.0, unmodifiableFunction.getY(2));
    }

    @Test
    public void testCreateStrictUnmodifiable() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 3.0, 5.0};

        TabulatedFunction strictUnmodifiableFunction = factory.createStrictUnmodifiable(xValues, yValues);

        assertThrows(UnsupportedOperationException.class, () -> {
            strictUnmodifiableFunction.setY(1, 10.0); 
        });


        assertThrows(UnsupportedOperationException.class, () -> {
            strictUnmodifiableFunction.apply(1.5); 
        });


        assertEquals(2.0, strictUnmodifiableFunction.getY(0));
        assertEquals(3.0, strictUnmodifiableFunction.getY(1));
        assertEquals(5.0, strictUnmodifiableFunction.getY(2));
    }

    @Test
    public void testCreateUnmodifiableWithShortArrays() {
        double[] xValues = {1.0};
        double[] yValues = {2.0};

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createUnmodifiable(xValues, yValues);
        });
    }

    @Test
    public void testCreateStrictUnmodifiableWithShortArrays() {
        double[] xValues = {1.0};
        double[] yValues = {2.0};

        assertThrows(IllegalArgumentException.class, () -> {
            factory.createStrictUnmodifiable(xValues, yValues); 
        });
    }
}
