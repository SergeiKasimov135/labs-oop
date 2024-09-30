package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class UnitFunctionTest {

    private final MathFunction unitFunction = new UnitFunction();

    @Test
    public void apply_ReturnsConstantValue1() {
        assertEquals(1., this.unitFunction.apply(5.));
        assertEquals(1., this.unitFunction.apply(17.3));
        assertEquals(1., this.unitFunction.apply(10.));

        assertEquals(1., this.unitFunction.apply(Double.POSITIVE_INFINITY));
        assertEquals(1., this.unitFunction.apply(Double.NEGATIVE_INFINITY));

        assertEquals(1., this.unitFunction.apply(Double.NaN));
    }
}