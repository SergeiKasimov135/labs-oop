package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class ZeroFunctionTest {

    private final MathFunction zeroFunction = new ZeroFunction();

    @Test
    public void apply_ReturnsConstantValue0() {
        assertEquals(0., this.zeroFunction.apply(5.));
        assertEquals(0., this.zeroFunction.apply(17.3));
        assertEquals(0., this.zeroFunction.apply(10.));

        assertEquals(0., this.zeroFunction.apply(Double.POSITIVE_INFINITY));
        assertEquals(0., this.zeroFunction.apply(Double.NEGATIVE_INFINITY));

        assertEquals(0., this.zeroFunction.apply(Double.NaN));
    }

}