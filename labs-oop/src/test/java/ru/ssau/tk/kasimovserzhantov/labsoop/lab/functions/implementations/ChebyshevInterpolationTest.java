package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChebyshevInterpolationTest {

    @Test
    public void testUnitFunction() {
        MathFunction unitFunction = new UnitFunction();
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(unitFunction, 0, 1, 5);
        for (double x = 0; x <= 1; x += 0.1) {
            assertEquals(1, interpolation.apply(x), 0.01);
        }
    }

    @Test
    public void testZeroFunction() {
        MathFunction zeroFunction = new ZeroFunction();
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(zeroFunction, 0, 1, 5);
        for (double x = 0; x <= 1; x += 0.1) {
            assertEquals(0, interpolation.apply(x), 0.01);
        }
    }

    @Test
    public void testSqrFunction() {
        MathFunction sqrFunction = new SqrFunction();
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(sqrFunction, 0, 1, 5);
        for (double x = 0; x <= 1; x += 0.1) {
            assertEquals(Math.pow(x, 2), interpolation.apply(x), 0.01);
        }
    }

    @Test
    public void testIdentityFunction() {
        MathFunction identityFunction = new IdentityFunction();
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(identityFunction, 0, 1, 5);
        for (double x = 0; x <= 1; x += 0.1) {
            assertEquals(x, interpolation.apply(x), 0.01);
        }
    }

    @Test
    public void testConstantFunction() {
        double constantValue = 5;
        MathFunction constantFunction = new ConstantFunction(constantValue);
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(constantFunction, 0, 1, 5);
        for (double x = 0; x <= 1; x += 0.1) {
            assertEquals(constantValue, interpolation.apply(x), 0.01);
        }
    }

    @Test
    public void testCompositeFunction() {
        MathFunction compositeFunction = new CompositeFunction(new SqrFunction(), new IdentityFunction());
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(compositeFunction, 0, 1, 5);
        for (double x = 0; x <= 1; x += 0.1) {
            assertEquals(Math.pow(x, 2), interpolation.apply(x), 0.01);
        }
    }
    
}
