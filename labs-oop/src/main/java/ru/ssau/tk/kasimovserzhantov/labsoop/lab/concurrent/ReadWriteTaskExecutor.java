package ru.ssau.tk.kasimovserzhantov.labsoop.lab.concurrent;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ConstantFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;

public class ReadWriteTaskExecutor {

    public static void main(String[] args) {
        TabulatedFunction linkedListTabulatedFunction =
                new LinkedListTabulatedFunction(new ConstantFunction(-1), 1, 1000, 1000);

        Thread thread1 = new Thread(new ReadTask(linkedListTabulatedFunction));
        Thread thread2 = new Thread(new WriteTask(linkedListTabulatedFunction, 0.5));

        thread1.start();
        thread2.start();
    }

}
