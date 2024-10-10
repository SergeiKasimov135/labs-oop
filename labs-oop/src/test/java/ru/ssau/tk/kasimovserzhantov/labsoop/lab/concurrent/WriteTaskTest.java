package ru.ssau.tk.kasimovserzhantov.labsoop.lab.concurrent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WriteTaskTest {
    private MockTabulatedFunction mockFunction;
    private WriteTask writeTask;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0, 4.0, 5.0};
        double[] yValues = new double[5];
        mockFunction = new MockTabulatedFunction(xValues, yValues);
        writeTask = new WriteTask(mockFunction, 10.0);
    }

    @Test
    public void testRun() {
        writeTask.run();

        for (int i = 0; i < mockFunction.getCount(); i++) {
            assertEquals(10.0, mockFunction.getY(i), 0.001);
        }
    }
}