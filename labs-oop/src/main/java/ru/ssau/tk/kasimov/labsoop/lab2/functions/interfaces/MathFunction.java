package ru.ssau.tk.kasimov.labsoop.lab2.functions.interfaces;

import ru.ssau.tk.kasimov.labsoop.lab2.functions.implementations.CompositeFunction;

public interface MathFunction {
    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
