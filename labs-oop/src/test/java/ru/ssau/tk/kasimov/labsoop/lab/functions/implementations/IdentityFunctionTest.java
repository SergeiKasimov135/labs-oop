package ru.ssau.tk.kasimov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimov.labsoop.lab.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class IdentityFunctionTest {

    private final MathFunction identifyFunction = new IdentityFunction();

    @Test
    void apply_ReturnsInputNumber() {
        assertEquals(5.2, this.identifyFunction.apply(5.2));
        assertEquals(4., this.identifyFunction.apply(4.));
        assertEquals(2.9, this.identifyFunction.apply(2.9));

        assertEquals(Double.POSITIVE_INFINITY, this.identifyFunction.apply(Double.POSITIVE_INFINITY));
        assertEquals(Double.NEGATIVE_INFINITY, this.identifyFunction.apply(Double.NEGATIVE_INFINITY));

        assertEquals(Double.NaN, this.identifyFunction.apply(Double.NaN));
    }
}