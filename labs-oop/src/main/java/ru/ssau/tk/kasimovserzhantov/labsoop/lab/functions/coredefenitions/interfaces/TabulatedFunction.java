package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;

import java.util.Iterator;

public interface TabulatedFunction extends MathFunction, Iterable<Point>, Insertable, Removable {

    int getCount();

    double getX(int index);

    double getY(int index);

    void setY(int index, double value);

    double [] getXValues();

    double [] getYValues();

    int indexOfX(double x);

    int indexOfY(double y);

    double leftBound();

    double rightBound();

    Iterator<Point> iterator();

}
