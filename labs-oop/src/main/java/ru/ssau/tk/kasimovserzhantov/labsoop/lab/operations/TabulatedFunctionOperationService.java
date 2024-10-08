package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {

    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    private interface BiOperation {

        double apply(double u, double v);

    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount())
            throw new InconsistentFunctionsException();

        Point[] pointsA = asPoints(a);
        Point[] pointsB = asPoints(b);
        double[] xValues = new double[pointsA.length];
        double[] yValues = new double[pointsA.length];

        for (int i = 0; i < pointsA.length; i++) {
            if (pointsA[i].getX() != pointsB[i].getX())
                throw new InconsistentFunctionsException();

            xValues[i] = pointsA[i].getX();
            yValues[i] = operation.apply(pointsA[i].getY(), pointsB[i].getY());
        }

        return factory.create(xValues, yValues);
    }

    public TabulatedFunction add(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiply(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b) throws InconsistentFunctionsException {
        return doOperation(a, b, (u, v) -> u / v);
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        Point[] points = new Point[tabulatedFunction.getCount()];
        int i = 0;
        for (Point point : tabulatedFunction) {
            points[i] = point;
            i++;
        }

        return points;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }
}
