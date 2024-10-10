package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations;

import org.junit.jupiter.api.Test;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        // Создаем оператор с шагом 0.1
        RightSteppingDifferentialOperator operator = new RightSteppingDifferentialOperator(0.1);

        // Определяем функцию f(x) = x^2
        MathFunction function = x -> x * x;

        // Получаем производную
        MathFunction derivative = operator.derive(function);

        // Проверяем значения производной в точках
        assertEquals(8.1, derivative.apply(4.0), 1e-5);
        assertEquals(2.1, derivative.apply(1.0), 1e-5);
    }

    @Test
    public void testIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> new RightSteppingDifferentialOperator(0));
        assertThrows(IllegalArgumentException.class, () -> new RightSteppingDifferentialOperator(-1));
        assertThrows(IllegalArgumentException.class, () -> new RightSteppingDifferentialOperator(Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> new RightSteppingDifferentialOperator(Double.POSITIVE_INFINITY));
    }
}
