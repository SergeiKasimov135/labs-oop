package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;

import java.io.*;
import java.nio.file.Paths;

public class TabulatedFunctionFileOutputStream {

    public static void main(String[] args) {
        try (BufferedOutputStream arrayOutputStream = new BufferedOutputStream(
                     new FileOutputStream(Paths.get("labs-oop/output", "array function.bin").toAbsolutePath().toString())
             );
             BufferedOutputStream linkedListOutputStream = new BufferedOutputStream(
                     new FileOutputStream(Paths.get("labs-oop/output", "linked list function.bin").toAbsolutePath().toString())
             )
        ) {
            TabulatedFunction arrayFunction = new
                    ArrayTabulatedFunction(new double[]{1.0, 2.5, 10.3}, new double[]{0.0, 0.25, 10.0});
            TabulatedFunction linkedListFunction = new
                    LinkedListTabulatedFunction(new double[]{1.2, 2.15, 3.0}, new double[]{-1.0, 1.75, 5.0});

            FunctionsIO.writeTabulatedFunction(arrayOutputStream, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListOutputStream, linkedListFunction);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
