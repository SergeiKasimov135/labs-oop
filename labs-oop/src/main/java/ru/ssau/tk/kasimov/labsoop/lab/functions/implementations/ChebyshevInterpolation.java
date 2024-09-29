package ru.ssau.tk.kasimov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimov.labsoop.lab.functions.coredefenitions.MathFunction;

public class ChebyshevInterpolation implements MathFunction {

    private final double[] coefficients;
    private final int degree;

    @Override
    public double apply(double x) {
        double result = 0;
        for (int i = 0; i <= degree; ++i) {
            result += coefficients[i] * chebyshevPolynomial(i, x);
        }

        return result;
    }

    private double chebyshevPolynomial(int n, double x) {
        if (n == 0)
            return 1;
        if (n == 1)
            return x;
        return 2 * x * chebyshevPolynomial(n - 1, x) - chebyshevPolynomial(n - 2, x);
    }

    public ChebyshevInterpolation(double[] coefficients) {
        this.coefficients = coefficients;
        this.degree = coefficients.length - 1;
    }

    public double[] getCoefficients() {
        return coefficients;
    }

    public int getDegree() {
        return degree;
    }
}
