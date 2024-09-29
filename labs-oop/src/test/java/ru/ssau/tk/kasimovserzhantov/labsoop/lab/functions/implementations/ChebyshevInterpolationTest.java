package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChebyshevInterpolationTest {

    @Test
    public void chebyshevPolynomial_0_Returns1() {
        // given
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(new double[]{1});
        int n = 0;
        double x = 0.5;

        // when
        double result = interpolation.apply(x);

        // then
        assertEquals(1, result, 1e-9);
    }

    @Test
    public void chebyshevPolynomial_1_ReturnsX() {
        // given
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(new double[]{0, 1});
        double x = 0.5;

        // when
        double result = interpolation.apply(x);

        // then
        assertEquals(x, result, 1e-9);
    }

    @Test
    public void chebyshevPolynomial_HigherOrder_ReturnsCorrectValue() {
        // given
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(new double[]{0, 0, 1});
        double x = 0.5;

        // when
        double result = interpolation.apply(x);

        // then
        double expected = 2 * x * x - 1;
        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void apply_SimpleCase_ReturnsCorrectValue() {
        // given
        double[] coefficients = {1, 2, 3};
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(coefficients);
        double x = 0.5;

        // when
        double result = interpolation.apply(x);

        // then
        double expected = coefficients[0] * 1 + // T0 = 1
                coefficients[1] * x +   // T1 = x
                coefficients[2] * (2 * x * x - 1); // T2 = 2x^2 - 1

        assertEquals(expected, result, 1e-9);
    }

    @Test
    public void constructor_CorrectInitialization() {
        // given
        double[] coefficients = {1, 2, 3};

        // when
        ChebyshevInterpolation interpolation = new ChebyshevInterpolation(coefficients);

        // then
        assertArrayEquals(coefficients, interpolation.getCoefficients());
        assertEquals(coefficients.length - 1, interpolation.getDegree());
    }
}