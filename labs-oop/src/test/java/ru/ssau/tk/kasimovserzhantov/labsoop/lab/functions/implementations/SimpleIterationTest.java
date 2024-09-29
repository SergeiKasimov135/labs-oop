package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.MathFunction;

import static org.junit.jupiter.api.Assertions.*;

class SimpleIterationTest {

    @Test
    public void apply_ConvergesToFixedPoint_ReturnsFixedPoint() {
        // given
        MathFunction g = x -> x * 0.5;
        SimpleIteration simpleIteration = new SimpleIteration(g, 1.0, 100, 1e-6);

        // when
        double result = simpleIteration.apply(0);

        // then
        assertEquals(0.0, result, 1e-6);
    }

    @Test
    public void apply_DoesNotConvergeWithinMaxIterations_ReturnsLastValue() {
        // given
        MathFunction g = x -> x + 1;
        SimpleIteration simpleIteration = new SimpleIteration(g, 1.0, 10, 1e-6);

        // when
        double result = simpleIteration.apply(0);

        // then
        assertNotEquals(0.0, result);
    }

    @Test
    public void apply_InitialGuessIsSolution_ReturnsInitialGuess() {
        // given
        MathFunction g = x -> x;
        SimpleIteration simpleIteration = new SimpleIteration(g, 5.0, 100, 0.0001);

        // when
        double result = simpleIteration.apply(0);

        // then
        assertEquals(5.0, result);
    }

    @Test
    public void apply_ToleranceIsMetInFirstIteration_ReturnsNextValue() {
        // given
        MathFunction g = x -> x;
        SimpleIteration simpleIteration = new SimpleIteration(g, 5.0, 100, 1e6);

        // when
        double result = simpleIteration.apply(0);

        // then
        assertEquals(5.0, result);
    }

    @Test
    public void constructor_NullFunction_ThrowsNullPointerException() {
        // given
        MathFunction g = null;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> new SimpleIteration(g, 1.0, 10, 1e-6));
    }

    @Test
    public void constructor_NegativeMaxIterations_ThrowsIllegalArgumentException() {
        // given
        MathFunction g = x -> x * 0.5;
        int maxIterations = -1;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> new SimpleIteration(g, 1.0, maxIterations, 1e-6));
    }

    @Test
    public void constructor_NonPositiveTolerance_ThrowsIllegalArgumentException() {
        // given
        MathFunction g = x -> x * 0.5;
        double tolerance = 0;

        // when and then
        assertThrows(IllegalArgumentException.class, () -> new SimpleIteration(g, 1.0, 10, tolerance));
    }

}