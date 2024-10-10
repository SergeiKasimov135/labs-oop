package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MiddleSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        // Создаем оператор с шагом 0.1
        MiddleSteppingDifferentialOperator operator = new MiddleSteppingDifferentialOperator(0.1);

        // Определяем функцию f(x) = x^2
        MathFunction function = x -> x * x;

        // Получаем производную
        MathFunction derivative = operator.derive(function);

        // Проверяем значения производной в точках
        assertEquals(6.0, derivative.apply(3.0), 1e-5);
        assertEquals(10.0, derivative.apply(5.0), 1e-5);
    }

    @Test
    public void testIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> new MiddleSteppingDifferentialOperator(0));
        assertThrows(IllegalArgumentException.class, () -> new MiddleSteppingDifferentialOperator(-1));
        assertThrows(IllegalArgumentException.class, () -> new MiddleSteppingDifferentialOperator(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new MiddleSteppingDifferentialOperator(Double.POSITIVE_INFINITY));
    }
}
