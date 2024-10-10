package ru.ssau.tk.kasimovserzhantov.labsoop.lab.concurrent;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MultiplyingTaskTest {

    private MockTabulatedFunction mockFunction;
    private MultiplyingTask multiplyingTask;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        mockFunction = new MockTabulatedFunction(xValues, yValues);
        multiplyingTask = new MultiplyingTask(mockFunction);
    }

    @Test
    public void testRun() {
        multiplyingTask.run();

        assertEquals(4.0, mockFunction.getY(0));
        assertEquals(8.0, mockFunction.getY(1));
        assertEquals(12.0, mockFunction.getY(2));
    }
}
