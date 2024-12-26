package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;

import java.io.*;
import java.nio.file.Paths;

public class ArrayTabulatedFunctionSerializationJson {

    public static void main(String[] args) {
        String filePath = Paths.get("labs-oop/output", "array tabulated function.json").toString();

        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new double[]{1, 2, 3}, new double[]{4, 5, 6});

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            FunctionsIO.serializeJson(writer, function);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayTabulatedFunction deserializedFunction = FunctionsIO.deserializeJson(reader);
            System.out.println("Deserialized function:\n" + deserializedFunction.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
