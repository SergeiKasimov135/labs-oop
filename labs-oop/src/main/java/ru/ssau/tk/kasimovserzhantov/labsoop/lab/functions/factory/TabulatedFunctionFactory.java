package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.StrictTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.UnmodifiableTabulatedFunction;

public interface TabulatedFunctionFactory {

    TabulatedFunction create(double[] xValues, double[] yValues);

    TabulatedFunction create(MathFunction source, double xFrom, double xTo, int count);

    default TabulatedFunction createStrict(double[] xValues, double[] yValues) {
        TabulatedFunction function = create(xValues, yValues);
        return new StrictTabulatedFunction(function);
    }

    default TabulatedFunction createUnmodifiable(double[] xValues, double[] yValues) {
        TabulatedFunction function = create(xValues, yValues);
        return new UnmodifiableTabulatedFunction(function);
    }

    default TabulatedFunction createStrictUnmodifiable(double[] xValues, double[] yValues) {
        TabulatedFunction function = create(xValues, yValues);
        return new UnmodifiableTabulatedFunction(new StrictTabulatedFunction(function));
    }

}
