package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {

    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

}
