package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class ArrayTabulatedFunctionFactoryTest {

    @Test
    void testCreate() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {1.0, 4.0, 9.0};

        TabulatedFunction function = factory.create(xValues, yValues);
        assertInstanceOf(ArrayTabulatedFunction.class, function, "Created function should be an instance of ArrayTabulatedFunction");
    }
}
