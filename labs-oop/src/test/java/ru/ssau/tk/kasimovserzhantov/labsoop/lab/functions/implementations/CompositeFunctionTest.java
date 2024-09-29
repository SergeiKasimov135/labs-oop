package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class CompositeFunctionTest {

    @Test
    public void apply_SimpleFunctionsGiven_ReturnsExpectedResult() {
        // Given
        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrFunction = new SqrFunction();
        MathFunction compositeFunction = new CompositeFunction(identityFunction, sqrFunction);
        double value = 5.;

        // When
        double expected = sqrFunction.apply(identityFunction.apply(value));
        double actual = compositeFunction.apply(value);

        // Then
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void apply_CompositeFunctionGiven_ReturnsExpectedResult() {
        // Given
        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrFunction = new SqrFunction();
        MathFunction compositeFunction1 = new CompositeFunction(sqrFunction, identityFunction);
        MathFunction compositeFunction2 = new CompositeFunction(compositeFunction1, identityFunction);
        double value = 2.;

        // When
        double expected = identityFunction.apply(compositeFunction1.apply(value));
        double actual = compositeFunction2.apply(value);

        // Then
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void apply_ConstantFunctionsGiven_ReturnsExpectedResult() {
        // Given
        MathFunction constantFunction = new ConstantFunction(1.0);
        MathFunction identityFunction = new IdentityFunction();
        MathFunction compositeFunction = new CompositeFunction(constantFunction, identityFunction);
        double value = 4.;

        // When
        double expected = identityFunction.apply(constantFunction.apply(value));
        double actual = compositeFunction.apply(value);

        // Then
        assertEquals(expected, actual, 0.0001);
    }

    @Test
    public void apply_SelfCompositionGiven_ReturnsExpectedResult() {
        // Given
        MathFunction sqrFunction = new SqrFunction();
        MathFunction compositeFunction = new CompositeFunction(sqrFunction, sqrFunction);
        double value = 2.;

        // When
        double expected = sqrFunction.apply(sqrFunction.apply(value));
        double actual = compositeFunction.apply(value);

        // Then
        assertEquals(expected, actual, 0.0001);
    }
}