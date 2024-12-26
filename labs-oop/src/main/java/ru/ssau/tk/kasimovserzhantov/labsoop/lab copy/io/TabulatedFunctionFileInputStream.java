package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations.TabulatedDifferentialOperator;

import java.io.*;
import java.nio.file.Paths;

public class TabulatedFunctionFileInputStream {

    public static void main(String[] args) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(Paths.get("labs-oop/input", "binary function.bin").toString())
            )
        ) {
            TabulatedFunctionFactory binFileFactory = new ArrayTabulatedFunctionFactory();
            TabulatedFunction function = FunctionsIO.readTabulatedFunction(bufferedInputStream, binFileFactory);

            System.out.println("Функция из файла: " + function.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Введите размер и значения функции (x y): ");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            TabulatedFunctionFactory consoleFactory = new LinkedListTabulatedFunctionFactory();
            TabulatedFunction consoleFunction = FunctionsIO.readTabulatedFunction(reader, consoleFactory);

            System.out.println("Функция из консоли: " + consoleFunction.toString());

            TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator(consoleFactory);
            TabulatedFunction derivative = differentialOperator.derive(consoleFunction);
            System.out.println("Производная функции: " + derivative.toString());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

}
