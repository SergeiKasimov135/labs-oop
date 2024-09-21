package ru.ssau.tk.kasimov.labsoop.lab2.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimov.labsoop.lab2.functions.interfaces.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class SqrFunctionTest {

    private final MathFunction sqrFunction = new SqrFunction();

    @Test
    void apply_ReturnsSquareOfInputNumber() {
        assertEquals(Math.pow(2., 2), this.sqrFunction.apply(2.), 0.0001);
        assertEquals(Math.pow(3., 2), this.sqrFunction.apply(3.), 0.0001);
        assertEquals(Math.pow(0., 2), this.sqrFunction.apply(0.), 0.0001);
        assertEquals(Math.pow(25., 2), this.sqrFunction.apply(25.), 0.0001);

        assertEquals(Math.pow(-2., 2), sqrFunction.apply(-2.), 0.001);
        assertEquals(Math.pow(-3., 2), sqrFunction.apply(-3.), 0.001);

        assertEquals(Double.POSITIVE_INFINITY, sqrFunction.apply(Double.POSITIVE_INFINITY));
        assertEquals(Double.POSITIVE_INFINITY, sqrFunction.apply(Double.NEGATIVE_INFINITY));
    }
}