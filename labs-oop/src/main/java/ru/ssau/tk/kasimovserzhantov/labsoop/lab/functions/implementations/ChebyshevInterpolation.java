package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

public class ChebyshevInterpolation implements MathFunction {

    private final MathFunction function;
    private final double[] nodes;

    public ChebyshevInterpolation(MathFunction function, double a, double b, int n) {
        this.function = function;
        nodes = new double[n + 1];
        for (int i = 0; i <= n; i++) {
            nodes[i] = 0.5 * (a + b) + 0.5 * (b - a) * Math.cos((Math.PI * (2 * i + 1)) / (2 * n + 2));
        }
    }

    @Override
    public double apply(double x) {
        double result = 0;
        for (int j = 0; j < nodes.length; j++) {
            double product = function.apply(nodes[j]);
            for (int k = 0; k < nodes.length; k++) {
                if (k != j) {
                    product *= (x - nodes[k]) / (nodes[j] - nodes[k]);
                }
            }
            result += product;
        }
        return result;
    }

}
