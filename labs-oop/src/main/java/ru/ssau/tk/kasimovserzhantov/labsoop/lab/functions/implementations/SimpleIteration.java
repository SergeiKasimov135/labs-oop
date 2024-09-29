package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;

public class SimpleIteration implements MathFunction {

    private MathFunction g;
    private final double initialGuess;
    private final int maxIterations;
    private final double tolerance;

    @Override
    public double apply(double x) {
        double curr = initialGuess;
        for (int i = 0; i < maxIterations; ++i) {
            double next = g.apply(curr);
            if (Math.abs(next - curr) < tolerance) {
                return next;
            }
            curr = next;
        }

        return curr;
    }

    public SimpleIteration(MathFunction g, double initialGuess, int maxIterations, double tolerance) {
        if (g == null)
            throw new IllegalArgumentException("Function cannot be null");

        if (maxIterations <= 0)
            throw new IllegalArgumentException("Maximum iterations must be positive");

        if (tolerance <= 0)
            throw new IllegalArgumentException("Tolerance must be positive");

        this.g = g;
        this.initialGuess = initialGuess;
        this.maxIterations = maxIterations;
        this.tolerance = tolerance;
    }
}