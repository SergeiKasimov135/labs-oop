package ru.ssau.tk.kasimovserzhantov.labsoop.lab.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

@RequiredArgsConstructor
@Getter
public class AnnotatedFunction {

    private final MathFunction function;

    private final int priority;

    private final String localizedName;

}
