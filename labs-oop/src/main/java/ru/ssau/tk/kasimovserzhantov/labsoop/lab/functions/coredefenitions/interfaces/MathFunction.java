package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.CompositeFunction;

public interface MathFunction {

    double apply(double x);

    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }

}
