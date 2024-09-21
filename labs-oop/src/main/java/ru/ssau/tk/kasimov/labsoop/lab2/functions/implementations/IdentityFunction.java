package ru.ssau.tk.kasimov.labsoop.lab2.functions.implementations;

import ru.ssau.tk.kasimov.labsoop.lab2.functions.interfaces.MathFunction;

public class IdentityFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return x;
    }

}
