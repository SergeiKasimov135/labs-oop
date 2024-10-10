package ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class InconsistentFunctionsExceptionTest {

    @Test
    void testDefaultConstructor() {
        InconsistentFunctionsException exception = new InconsistentFunctionsException();
        assertNotNull(exception); // Проверка, что исключение не null
    }

    @Test
    void testMessageConstructor() {
        String message = "Обнаружены несоответствующие функции.";
        InconsistentFunctionsException exception = new InconsistentFunctionsException(message);

        assertNotNull(exception); // Проверка, что исключение не null
        assertEquals(message, exception.getMessage()); // Проверка, что сообщение соответствует ожидаемому
    }
}
