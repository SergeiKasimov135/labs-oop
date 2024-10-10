package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.coredefenitions.abstractclasses;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

public class SteppingDifferentialOperatorTest {

    private static class TestSteppingDifferentialOperator extends SteppingDifferentialOperator {
        public TestSteppingDifferentialOperator(double step) {
            super(step);
        }


        @Override
        public MathFunction derive(MathFunction function) {
            return null;
        }
    }

    @Test
    public void testConstructorValidStep() {
        TestSteppingDifferentialOperator operator = new TestSteppingDifferentialOperator(0.1);
        assertNotNull(operator);
        assertEquals(0.1, operator.getStep());
    }

    @Test
    public void testConstructorZeroStep() {
        assertThrows(IllegalArgumentException.class, () -> new TestSteppingDifferentialOperator(0));
    }

    @Test
    public void testConstructorNegativeStep() {
        assertThrows(IllegalArgumentException.class, () -> new TestSteppingDifferentialOperator(-0.1));
    }

    @Test
    public void testConstructorNaNStep() {
        assertThrows(IllegalArgumentException.class, () -> new TestSteppingDifferentialOperator(Double.NaN));
    }

    @Test
    public void testConstructorInfiniteStep() {
        assertThrows(IllegalArgumentException.class, () -> new TestSteppingDifferentialOperator(Double.POSITIVE_INFINITY));
    }

    @Test
    public void testGetStep() {
        TestSteppingDifferentialOperator operator = new TestSteppingDifferentialOperator(0.5);
        assertEquals(0.5, operator.getStep());
    }

    @Test
    public void testSetStep() {
        TestSteppingDifferentialOperator operator = new TestSteppingDifferentialOperator(0.5);
        operator.setStep(0.2);
        assertEquals(0.2, operator.getStep());
    }

}
