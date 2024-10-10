package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

public class TabulatedFunctionOperationServiceTest {

    @Test
    public void testAsPoints() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 3.0, 5.0};
        TabulatedFunction function = new ArrayTabulatedFunction(xValues, yValues);

        Point[] expectedPoints = {
                new Point(1.0, 2.0),
                new Point(2.0, 3.0),
                new Point(3.0, 5.0)
        };

        Point[] actualPoints = TabulatedFunctionOperationService.asPoints(function);

        assertArrayEquals(expectedPoints, actualPoints);
    }

    @Test
    public void testAdditionSameType() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {1.0, 2.0, 3.0};
        double[] yValues2 = {4.0, 5.0, 6.0};
        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYValues = {5.0, 7.0, 9.0};

        TabulatedFunction result = service.add(function1, function2);
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedYValues[i], result.getY(i));
        }
    }

    @Test
    public void testSubtractionSameType() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {5.0, 7.0, 9.0};
        double[] yValues2 = {1.0, 2.0, 3.0};
        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYValues = {4.0, 5.0, 6.0};

        TabulatedFunction result = service.subtract(function1, function2);
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedYValues[i], result.getY(i));
        }
    }

    @Test
    public void testInconsistentFunctionsException() {
        double[] xValues1 = {0.0, 1.0, 2.0};
        double[] yValues1 = {1.0, 2.0, 3.0};
        double[] xValues2 = {0.0, 1.0, 3.0}; 
        double[] yValues2 = {4.0, 5.0, 6.0};

        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues1, yValues1);
        TabulatedFunction function2 = new ArrayTabulatedFunction(xValues2, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        assertThrows(InconsistentFunctionsException.class, () -> service.add(function1, function2));
        assertThrows(InconsistentFunctionsException.class, () -> service.subtract(function1, function2));
    }

    @Test
    public void testAdditionDifferentTypes() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {1.0, 2.0, 3.0};
        double[] yValues2 = {4.0, 5.0, 6.0};

        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYValues = {5.0, 7.0, 9.0};

        TabulatedFunction result = service.add(function1, function2);
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedYValues[i], result.getY(i));
        }
    }
    @Test
    public void testMultiplySameType() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {1.0, 2.0, 3.0};
        double[] yValues2 = {4.0, 5.0, 6.0};
        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYValues = {4.0, 10.0, 18.0};

        TabulatedFunction result = service.multiply(function1, function2);
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedYValues[i], result.getY(i));
        }
    }

    @Test
    public void testDivisionSameType() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {4.0, 10.0, 18.0};
        double[] yValues2 = {2.0, 2.0, 2.0};
        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYValues = {2.0, 5.0, 9.0};

        TabulatedFunction result = service.division(function1, function2);
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedYValues[i], result.getY(i));
        }
    }

    @Test
    public void testDivisionByZero() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {4.0, 10.0, 18.0};
        double[] yValues2 = {0.0, 0.0, 0.0}; 
        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new ArrayTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        assertThrows(ArithmeticException.class, () -> service.division(function1, function2));
    }

    @Test
    public void testMultiplyDifferentTypes() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {1.0, 2.0, 3.0};
        double[] yValues2 = {4.0, 5.0, 6.0};

        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYValues = {4.0, 10.0, 18.0};

        TabulatedFunction result = service.multiply(function1, function2);
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedYValues[i], result.getY(i));
        }
    }

    @Test
    public void testDivisionDifferentTypes() {
        double[] xValues = {0.0, 1.0, 2.0};
        double[] yValues1 = {4.0, 10.0, 18.0};
        double[] yValues2 = {2.0, 2.0, 2.0};

        TabulatedFunction function1 = new ArrayTabulatedFunction(xValues, yValues1);
        TabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues2);

        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();

        double[] expectedYValues = {2.0, 5.0, 9.0};

        TabulatedFunction result = service.division(function1, function2);
        for (int i = 0; i < result.getCount(); i++) {
            assertEquals(expectedYValues[i], result.getY(i));
        }
    }

    @Test
    public void testDefaultConstructor() {
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        assertNotNull(service.getFactory());
        assertInstanceOf(ArrayTabulatedFunctionFactory.class, service.getFactory());
    }

    @Test
    public void testParameterizedConstructor() {
        TabulatedFunctionFactory customFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(customFactory);
        assertEquals(customFactory, service.getFactory());
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionFactory customFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService();
        service.setFactory(customFactory);
        assertEquals(customFactory, service.getFactory());
    }

    @Test
    public void testGetFactory() {
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionOperationService service = new TabulatedFunctionOperationService(factory);
        assertEquals(factory, service.getFactory());
    }
}
