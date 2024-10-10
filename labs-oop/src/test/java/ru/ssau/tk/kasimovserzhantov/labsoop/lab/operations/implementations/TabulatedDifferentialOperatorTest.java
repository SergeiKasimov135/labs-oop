package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class TabulatedDifferentialOperatorTest {

    private TabulatedFunction tabulatedFunction;
    private TabulatedDifferentialOperator differentialOperator;

    private TabulatedFunction createArrayTabulatedFunction() {
        return new ArrayTabulatedFunction(new double[]{-3, 1.5, 6, 10.5, 15}, new double[]{9, 2.25, 36, 110.25, 225});
    }

    private TabulatedFunction createLinkedListTabulatedFunction() {
        return new LinkedListTabulatedFunction(new double[]{-3, 1.5, 6, 10.5, 15}, new double[]{9, 2.25, 36, 110.25, 225});
    }

    @Test
    void testArrayTabulatedFunction() {
        tabulatedFunction = createArrayTabulatedFunction();
        differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction derivedFunction = differentialOperator.derive(tabulatedFunction);

        assertInstanceOf(ArrayTabulatedFunction.class, derivedFunction);
        assertEquals(16.5, derivedFunction.apply(6), 0.01);
    }

    @Test
    void testListTabulatedFunction() {
        tabulatedFunction = createLinkedListTabulatedFunction();
        differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction derivedFunction = differentialOperator.derive(tabulatedFunction);

        assertInstanceOf(LinkedListTabulatedFunction.class, derivedFunction);
        assertEquals(16.5, derivedFunction.apply(6), 0.01);
    }

    @Test
    void testDeriveSynchronously() {
        tabulatedFunction = createLinkedListTabulatedFunction();
        differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction derivedFunction = differentialOperator.deriveSynchronously(tabulatedFunction);

        assertEquals(16.5, derivedFunction.apply(6), 0.01);
    }

    @Test
    void testGetFactory() {
        differentialOperator = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, differentialOperator.getFactory());
    }

    @Test
    void testSetFactory() {
        differentialOperator = new TabulatedDifferentialOperator();
        LinkedListTabulatedFunctionFactory factory = new LinkedListTabulatedFunctionFactory();
        differentialOperator.setFactory(factory);
        assertInstanceOf(LinkedListTabulatedFunctionFactory.class, differentialOperator.getFactory());
    }

}
