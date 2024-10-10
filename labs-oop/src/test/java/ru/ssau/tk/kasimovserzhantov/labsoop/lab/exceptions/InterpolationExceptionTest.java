package ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterpolationExceptionTest {

    @Test
    public void testDefaultConstructor() {
        // given
        InterpolationException exception = new InterpolationException();

        // when & then
        assertNull(exception.getMessage(), "Сообщение должно быть null");
    }

    @Test
    public void testMessageConstructor() {
        // given
        String message = "Ошибка интерполяции";
        InterpolationException exception = new InterpolationException(message);

        // when & then
        assertEquals(message, exception.getMessage(), "Сообщение должно соответствовать заданному");
    }

}
