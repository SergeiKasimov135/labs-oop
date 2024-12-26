package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FunctionInfo {
    String name();
    int priority();
}