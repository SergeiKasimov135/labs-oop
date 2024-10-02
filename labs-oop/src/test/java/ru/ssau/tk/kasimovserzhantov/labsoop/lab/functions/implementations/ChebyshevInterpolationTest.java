package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChebyshevInterpolationTest {

    @Test
    public void testApply_SqrFunction_ReturnsExpectedValues() {
        MathFunction sqrFunction = new SqrFunction();
        ChebyshevInterpolation interpolation = new
                ChebyshevInterpolation(sqrFunction, 0, 1, 5);

        assertEquals(0.0, interpolation.apply(0.0), 0.0001);
        assertEquals(0.25, interpolation.apply(0.5), 0.0001);
        assertEquals(1.0, interpolation.apply(1.0), 0.0001);
    }

    @Test
    public void testApply_UnitFunction_ReturnsExpectedValues() {
        MathFunction unitFunction = new UnitFunction();
        ChebyshevInterpolation interpolation = new
                ChebyshevInterpolation(unitFunction, 0, 1, 5);

        assertEquals(1.0, interpolation.apply(0.0), 0.0001);
        assertEquals(1.0, interpolation.apply(0.5), 0.0001);
        assertEquals(1.0, interpolation.apply(1.0), 0.0001);
    }

    @Test
    public void testApply_ZeroFunction_ReturnsExpectedValues() {
        MathFunction zeroFunction = new ZeroFunction();
        ChebyshevInterpolation interpolation = new
                ChebyshevInterpolation(zeroFunction, 0, 1, 5);

        assertEquals(0.0, interpolation.apply(0.0), 0.0001);
        assertEquals(0.0, interpolation.apply(0.5), 0.0001);
        assertEquals(0.0, interpolation.apply(1.0), 0.0001);
    }

    @Test
    public void testApply_CompositeFunction_ReturnsExpectedValues() {
        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrFunction = new SqrFunction();
        MathFunction compositeFunction = new CompositeFunction(identityFunction, sqrFunction);

        ChebyshevInterpolation interpolation = new
                ChebyshevInterpolation(compositeFunction, 0, 1, 5);

        assertEquals(0.0, interpolation.apply(0.0), 0.0001);
        assertEquals(0.25, interpolation.apply(0.5), 0.0001);
        assertEquals(1.0, interpolation.apply(1.0), 0.0001);
    }

    @Test
    public void testApply_NegativeValues_ReturnsExpectedValues() {
        MathFunction sqrFunction = new SqrFunction();
        ChebyshevInterpolation interpolation = new
                ChebyshevInterpolation(sqrFunction, -1, 0, 5);

        assertEquals(1.0, interpolation.apply(-1.0), 0.0001);
        assertEquals(0.25, interpolation.apply(-0.5), 0.0001);
        assertEquals(0.0, interpolation.apply(0.0), 0.0001);
    }
    
}