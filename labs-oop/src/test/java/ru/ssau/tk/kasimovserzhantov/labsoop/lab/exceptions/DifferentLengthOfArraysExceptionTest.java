package ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DifferentLengthOfArraysExceptionTest {

    @Test
    public void testDefaultConstructor() {
        // given
        DifferentLengthOfArraysException exception = new DifferentLengthOfArraysException();

        // when & then
        assertNull(exception.getMessage(), "Сообщение должно быть null");
    }

    @Test
    public void testMessageConstructor() {
        // given
        String message = "Длины массивов не совпадают";
        DifferentLengthOfArraysException exception = new DifferentLengthOfArraysException(message);

        // when & then
        assertEquals(message, exception.getMessage(), "Сообщение должно соответствовать заданному");
    }

}
