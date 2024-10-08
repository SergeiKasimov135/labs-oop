package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.coredefenitions.abstractclasses;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.coredefenitions.interfaces.DifferentialOperator;

public abstract class SteppingDifferentialOperator implements DifferentialOperator<MathFunction> {

    protected double step;

    public SteppingDifferentialOperator(double step) {
        if (step <= 0 || Double.isNaN(step) || Double.isInfinite(step))
            throw new IllegalArgumentException();

        this.step = step;
    }

    public double getStep() {
        return step;
    }

    public void setStep(double step) {
        this.step = step;
    }
}
