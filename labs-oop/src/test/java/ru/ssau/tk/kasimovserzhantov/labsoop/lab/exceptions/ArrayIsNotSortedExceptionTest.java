package ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayIsNotSortedExceptionTest {

    @Test
    public void testDefaultConstructor() {
        // given
        ArrayIsNotSortedException exception = new ArrayIsNotSortedException();

        // when & then
        assertNull(exception.getMessage(), "Сообщение должно быть null");
    }

    @Test
    public void testMessageConstructor() {
        // given
        String message = "Массив не отсортирован";
        ArrayIsNotSortedException exception = new ArrayIsNotSortedException(message);

        // when & then
        assertEquals(message, exception.getMessage(), "Сообщение должно соответствовать заданному");
    }

}
