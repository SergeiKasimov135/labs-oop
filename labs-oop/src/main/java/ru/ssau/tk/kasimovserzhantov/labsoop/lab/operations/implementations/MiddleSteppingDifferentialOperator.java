package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.coredefenitions.abstractclasses.SteppingDifferentialOperator;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {

    public MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return new MathFunction() {
            @Override
            public double apply(double x) {
                return (function.apply(x + step / 2) - function.apply(x - step / 2)) / step;
            }
        };
    }

}
