package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.coredefenitions.interfaces;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

public interface DifferentialOperator<T extends MathFunction> {

    T derive(T function);

}
