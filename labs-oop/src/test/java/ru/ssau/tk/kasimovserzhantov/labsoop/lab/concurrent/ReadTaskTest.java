package ru.ssau.tk.kasimovserzhantov.labsoop.lab.concurrent;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ReadTaskTest {

    private MockTabulatedFunction mockFunction;
    private ReadTask readTask;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        mockFunction = new MockTabulatedFunction(xValues, yValues);
        readTask = new ReadTask(mockFunction);

        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testRun() {
        readTask.run();

        String output = outputStream.toString();
        assertTrue(output.contains("After read: i = 0, x = 1.000000, y = 2.000000"));
        assertTrue(output.contains("After read: i = 1, x = 2.000000, y = 4.000000"));
        assertTrue(output.contains("After read: i = 2, x = 3.000000, y = 6.000000"));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }
}