package ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.coredefenitions.interfaces.DifferentialOperator;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {

    private TabulatedFunctionFactory factory;

    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperationService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];

        double h = 0.00001;
        for (int i = 1; i < points.length - 1; i++) {
            xValues[i] = points[i].getX();
            yValues[i] = (points[i + 1].getY() - points[i - 1].getY()) / (2 * h);
        }

        xValues[0] = points[0].getX();
        yValues[0] = (points[1].getY() - points[0].getY()) / h;
        xValues[points.length - 1] = points[points.length - 1].getX();
        yValues[points.length - 1] = (points[points.length - 1].getY() - points[points.length - 2].getY()) / h;

        return factory.create(xValues, yValues);
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

}
