package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;


public class TabulatedFunctionFileReader {

    public static void main(String[] args) {
        Path path = Paths.get("labs-oop/input", "function.txt");

        try (BufferedReader arrayFileReader = new BufferedReader(
                     new FileReader(path.toAbsolutePath().toString())
             );
             BufferedReader linkedListFileReader = new BufferedReader(
                     new FileReader(path.toAbsolutePath().toString())
             )
        ) {
            TabulatedFunctionFactory arrayFactory = new ArrayTabulatedFunctionFactory();
            TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();

            TabulatedFunction arrayFunction = FunctionsIO.readTabulatedFunction(arrayFileReader, arrayFactory);
            TabulatedFunction linkedListFunction = FunctionsIO.readTabulatedFunction(linkedListFileReader, linkedListFactory);

            System.out.println(arrayFunction.toString());
            System.out.println(linkedListFunction.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
