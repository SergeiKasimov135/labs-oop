package ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions;

public class RemoveIncorrectPoint extends RuntimeException {
    public RemoveIncorrectPoint() {}

    public RemoveIncorrectPoint(String message) {
        super(message);
    }
}