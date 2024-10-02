package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.SqrFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.IdentityFunction;

import static org.junit.jupiter.api.Assertions.*;

class MathFunctionTest {

    @Test
    public void andThen_ReturnsSqrAndIdentityFunctionsResult() {
        // Given
        MathFunction sqrFunction = new SqrFunction();
        MathFunction identityFunction = new IdentityFunction();
        MathFunction composeFunction = sqrFunction.andThen(identityFunction);

        // When
        double value = 2.;
        double actual = composeFunction.apply(value);

        // Then
        assertEquals(4., actual, 0.0001);
    }

    @Test
    public void andThen_ReturnsIdentityAndSqrFunctionsResult() {
        // Given
        MathFunction identityFunction = new IdentityFunction();
        MathFunction sqrFunction = new SqrFunction();
        MathFunction compositeFunction = identityFunction.andThen(sqrFunction);

        // When
        double value = 2.;
        double actual = compositeFunction.apply(value);

        // Then
        assertEquals(4., actual, 0.0001);
    }

    @Test
    public void andThen_ReturnsCompositeFunctionResult() {
        // Given
        MathFunction sqrFunction = new SqrFunction();
        MathFunction identityFunction = new IdentityFunction();
        MathFunction composeFunction1 = sqrFunction.andThen(identityFunction);
        MathFunction composeFunction2 = composeFunction1.andThen(sqrFunction);

        // When
        double value = 2.;
        double actual = composeFunction2.apply(value);

        // Then
        assertEquals(16., actual, 0.0001);
    }

}