package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantFunctionTest {

    private final ConstantFunction constantFunction = new ConstantFunction(5.2);

    @Test
    public void apply_ReturnsConstantValueSpecializedInConstructor() {
        assertEquals(5.2, this.constantFunction.apply(5.));
        assertEquals(5.2, this.constantFunction.apply(17.3));
        assertEquals(5.2, this.constantFunction.apply(10.));

        assertEquals(5.2, this.constantFunction.apply(Double.POSITIVE_INFINITY));
        assertEquals(5.2, this.constantFunction.apply(Double.NEGATIVE_INFINITY));

        assertEquals(5.2, this.constantFunction.apply(Double.NaN));
    }

    @Test
    public void getConstant_ReturnsConstantValueSpecializedInConstructor() {
        assertEquals(5.2, this.constantFunction.getConstant());
    }

}