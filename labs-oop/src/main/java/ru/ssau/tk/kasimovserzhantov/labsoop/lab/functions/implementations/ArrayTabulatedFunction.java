package ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.InterpolationException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.Insertable;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.Removable;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.abstractclasses.AbstractTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {

    @Serial
    private static final long serialVersionUID = 1901617932477942620L;

    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        AbstractTabulatedFunction.checkLengthIsTheSame(xValues, yValues);
        AbstractTabulatedFunction.checkSorted(xValues);

        if (xValues.length < 2) {
            throw new IllegalArgumentException("Array's length should be at least 2");
        }

        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Count should be at least 2");
        }

        if (source == null) {
            throw new IllegalArgumentException("Source can't be a null");
        }

        if (xFrom > xTo) {
            double tmp = xTo;
            xTo = xFrom;
            xFrom = tmp;
        }

        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];

        if (xFrom == xTo) {
            double y = source.apply(xFrom);

            Arrays.fill(this.xValues, xFrom);
            Arrays.fill(this.yValues, y);
        } else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; ++i) {
                this.xValues[i] = xFrom + step * i;
                this.yValues[i] = source.apply(this.xValues[i]);
            }
        }
    }

    private void isIndexValid(int index) {
        if (index < 0 || index >= getCount())
            throw new IndexOutOfBoundsException();
    }

    private double extrapolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) / (rightX - leftX) * (x - leftX);
    }

    private int findInsertIndex(double x) {
        int left = 0;
        int right = count - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (xValues[mid] == x) {
                return mid;
            } else if (xValues[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    @Override
    public void insert(double x, double y) {
        if (Double.isNaN(x) || Double.isNaN(y))
            throw new IllegalArgumentException("X and Y should be a valid number");

        int indexOfX = indexOfX(x);
        if (indexOfX != -1) {
            yValues[indexOfX] = y;
            return;
        }

        if (count == xValues.length) {
            xValues = Arrays.copyOf(xValues, xValues.length * 2);
            yValues = Arrays.copyOf(yValues, yValues.length * 2);
        }

        int insertIndex = findInsertIndex(x);

        System.arraycopy(xValues, insertIndex, xValues, insertIndex + 1, count - insertIndex);
        System.arraycopy(yValues, insertIndex, yValues, insertIndex + 1, count - insertIndex);

        xValues[insertIndex] = x;
        yValues[insertIndex] = y;

        count++;
    }

    @Override
    public void remove(int index) {
        isIndexValid(index);

        if (count == 1) {
            xValues = null;
            yValues = null;

            count = 0;
        } else {
            System.arraycopy(xValues, index + 1, xValues, index, count - index - 1);
            System.arraycopy(yValues, index + 1, yValues, index, count - index - 1);
            count--;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        isIndexValid(index);
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        isIndexValid(index);
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        isIndexValid(index);
        yValues[index] = value;
    }

    @Override
    public int indexOfX(double x) {
        int idx = Arrays.binarySearch(xValues, x);
        return idx >= 0 ? idx : -1;
    }

    @Override
    public int indexOfY(double y) {
        int idx = Arrays.binarySearch(yValues, y);
        return idx >= 0 ? idx : -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[xValues.length - 1];
    }

    @Override
    public Iterator<Point> iterator() {
        return new Iterator<Point>() {
            private int i = 0;

            @Override
            public boolean hasNext() {
                return i < count;
            }

            @Override
            public Point next() {
                if (!hasNext())
                    throw new NoSuchElementException("");

                return new Point(xValues[i], yValues[i++]);
            }
        };
    }

    @Override
    protected int floorIndexOfX(double x) {
        int index = Arrays.binarySearch(xValues, x);
        if (index >= 0) {
            return index;
        } else {
            return -(index + 2);
        }
    }

    @Override
    protected double extrapolateLeft(double x) {
        return count == 1 ? yValues[0] : extrapolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return count == 1 ? yValues[0] : extrapolate(x, xValues[getCount() - 2], xValues[getCount() - 1],
                yValues[getCount() - 2], yValues[getCount() - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x > xValues[floorIndex + 1] || x < xValues[floorIndex])
            throw new InterpolationException("x is out of the interpolation interval");

        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1],
                yValues[floorIndex], yValues[floorIndex + 1]);
    }

}
