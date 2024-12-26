package ru.ssau.tk.kasimovserzhantov.labsoop.lab.concurrent;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;

import java.util.concurrent.Callable;

public class IntegralTask implements Callable<Double> {

    private final TabulatedFunction function;
    private final double a;
    private final double b;

    public IntegralTask(TabulatedFunction function, double a, double b) {

        if(a > b){
            throw new IllegalArgumentException("a can not be > b");
        }

        if (function == null){
            throw new IllegalArgumentException("Function is null");
        }

        this.function = function;
        this.a = a;
        this.b = b;
    }

    public double solveByTrapezoidalRule(int n){
        if(n <= 0){
            throw new IllegalArgumentException("n can not be <= 0");
        }

        double h = (b - a)/n;
        double result = 0.0;

        for(int i = 0;i < n;i++){
            double x0 = a + i * h;
            double x1 = a + (i+1) * h;
            result+= (function.apply(x0) + function.apply(x1)) * h/ 2;
        }

        return result;
    }

    public Double call(){
        return solveByTrapezoidalRule(1000);
    }

}