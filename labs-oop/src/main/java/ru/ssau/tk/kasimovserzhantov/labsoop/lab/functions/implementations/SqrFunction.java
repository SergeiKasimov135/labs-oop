package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

public class SqrFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return Math.pow(x, 2);
    }

}