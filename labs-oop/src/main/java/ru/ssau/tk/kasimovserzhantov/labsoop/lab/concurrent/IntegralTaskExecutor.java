package ru.ssau.tk.kasimovserzhantov.labsoop.lab.concurrent;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IntegralTaskExecutor {

    private final ExecutorService executor;
    private final int countThreads;

    public IntegralTaskExecutor(){
        this.countThreads = Runtime.getRuntime().availableProcessors() - 1;
        this.executor = Executors.newFixedThreadPool(countThreads);
    }


    public IntegralTaskExecutor(int countThreads){
        this.executor = Executors.newFixedThreadPool(countThreads);
        this.countThreads = countThreads;
    }

    public double itegrate(TabulatedFunction function) throws ExecutionException, InterruptedException {
        double result = 0;

        double delta = Math.abs((function.rightBound() - function.leftBound()))/countThreads;

        List<Future<Double>> futures = new ArrayList<>();

        for(int i = 0;i < countThreads;i++){

            double a = function.leftBound() + i * delta;
            double b = a + delta;
            IntegralTask integral = new IntegralTask(function,a,b);

            futures.add(executor.submit(integral));
        }

        for (Future<Double> future : futures) {
            result += future.get();
        }

        return result;
    }

    //Остановка сервиса
    public void shutdown() {
        executor.shutdown();
    }
}