package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;

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