package ru.ssau.tk.kasimov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimov.labsoop.lab.functions.coredefenitions.MathFunction;

public class IdentityFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return x;
    }

}
