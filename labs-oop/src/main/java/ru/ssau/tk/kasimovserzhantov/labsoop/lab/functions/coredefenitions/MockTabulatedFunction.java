package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.abstractclasses.AbstractTabulatedFunction;

public class MockTabulatedFunction extends AbstractTabulatedFunction {

    private final double x0, x1;
    private double y0, y1;

    public MockTabulatedFunction(double x0, double x1, double y0, double y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }

    private void isIndexValid(int index) {
        if (!(index == 0 || index == 1))
            throw new IndexOutOfBoundsException("Mock object has only two coords");
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public double getX(int index) {
        isIndexValid(index);
        return index == 0 ? x0 : x1;
    }

    @Override
    public double getY(int index) {
        isIndexValid(index);
        return index == 0 ? y0 : y1;
    }

    @Override
    public void setY(int index, double value) {
        isIndexValid(index);
        if (index == 0) {
            y0 = value;
        } else {
            y1 = value;
        }
    }

    @Override
    public int indexOfX(double x) {
        if (x == x0) return 0;
        if (x == x1) return 1;
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        if (y == y0) return 0;
        if (y == y1) return 1;
        return -1;
    }

    @Override
    public double leftBound() {
        return x0;
    }

    @Override
    public double rightBound() {
        return x1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < x0) return 0;
        if (x >= x1) return 1;
        return 0;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return y0;
    }

    @Override
    protected double extrapolateRight(double x) {
        return y1;
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, x0, x1, y0, y1);
    }

}
