package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

public class TabulatedFunctionFileWriter {

    public static void main(String[] args) {
        try (BufferedWriter arrayFileWriter = new BufferedWriter(
                     new FileWriter(Paths.get("labs-oop/output", "array function.txt").toAbsolutePath().toString())
             );
             BufferedWriter linkedListFileWriter = new BufferedWriter(
                     new FileWriter(Paths.get("labs-oop/output", "linked list function.txt").toAbsolutePath().toString())
             )
        ) {
            TabulatedFunction arrayFunction = new
                    ArrayTabulatedFunction(new double[]{0.0, 0.5, 1.0}, new double[]{0.0, 0.25, 10.0});
            TabulatedFunction linkedListFunction = new
                    LinkedListTabulatedFunction(new double[]{0.0, 0.15, 3.0}, new double[]{0.0, 0.75, 5.0});

            FunctionsIO.writeTabulatedFunction(arrayFileWriter, arrayFunction);
            FunctionsIO.writeTabulatedFunction(linkedListFileWriter, linkedListFunction);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
