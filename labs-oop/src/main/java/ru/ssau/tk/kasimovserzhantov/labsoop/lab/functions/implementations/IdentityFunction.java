package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;

public class IdentityFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return x;
    }

}