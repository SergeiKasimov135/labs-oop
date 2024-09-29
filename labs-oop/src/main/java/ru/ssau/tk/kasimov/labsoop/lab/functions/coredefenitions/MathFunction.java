package ru.ssau.tk.kasimov.labsoop.lab.functions.coredefenitions;

import ru.ssau.tk.kasimov.labsoop.lab.functions.implementations.CompositeFunction;

public interface MathFunction {

    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }

}
