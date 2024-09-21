package ru.ssau.tk.kasimov.labsoop.lab2.functions.implementations;

import ru.ssau.tk.kasimov.labsoop.lab2.functions.interfaces.MathFunction;

public class CompositeFunction implements MathFunction {
    private final MathFunction firstFunction;
    private final MathFunction secondFunction;

    @Override
    public double apply(double x) {
        return secondFunction.apply(firstFunction.apply(x));
    }

    public CompositeFunction(MathFunction firstFunction, MathFunction secondFunction) {
        this.firstFunction = firstFunction;
        this.secondFunction = secondFunction;
    }
}
