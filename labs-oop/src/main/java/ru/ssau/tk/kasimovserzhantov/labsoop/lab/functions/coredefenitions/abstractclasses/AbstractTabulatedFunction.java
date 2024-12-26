package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.abstractclasses;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.DifferentLengthOfArraysException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    protected int count;

    public static void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Arrays must be the same length");
        }
    }

    public static void checkSorted(double[] xValues) {
        for (int i = 1; i < xValues.length; ++i) {
            if (xValues[i] < xValues[i - 1])
                throw new ArrayIsNotSortedException("Array must be sorted in increasing order");
        }
    }

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else {
            int index = indexOfX(x);
            if (index != -1) {
                return getY(index);
            } else {
                return interpolate(x, floorIndexOfX(x));
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(getClass().getSimpleName() + " size = " + getCount() + "\n");
        for (Point point : this) {
            string.append("[").append(point.getX()).append("; ").append(point.getY()).append("]\n");
        }

        return string.toString();
    }
}
