package ru.ssau.tk.kasimov.labsoop.lab2.functions.implementations;

import ru.ssau.tk.kasimov.labsoop.lab2.functions.interfaces.MathFunction;

public class ConstantFunction implements MathFunction {

    private final double constant;

    @Override
    public double apply(double x) {
        return constant;
    }

    public ConstantFunction(double constant) {
        this.constant = constant;
    }

    public double getConstant() {
        return constant;
    }
}
