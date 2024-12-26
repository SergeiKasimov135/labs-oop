package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations.TabulatedDifferentialOperator;

import java.io.*;
import java.nio.file.Paths;

public class ArrayTabulatedFunctionSerialization {

    public static void main(String[] args) {
        String filePath = Paths.get("output", "serialized array functions.bin").toString();

        TabulatedFunction arrayTabulatedFunction =
                new ArrayTabulatedFunction(new double[]{1.7, 2.8, 6.5}, new double[]{5.0, 8.3, 9.19});

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction firstDerivative = differentialOperator.derive(arrayTabulatedFunction);
        TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            FunctionsIO.serialize(bufferedOutputStream, arrayTabulatedFunction);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedArrayTabulatedFunction = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("Исходная функция: " + deserializedArrayTabulatedFunction.toString());
            System.out.println("Первая производная исходной функции: " + deserializedFirstDerivative.toString());
            System.out.println("Вторая производная исходной функции: " + deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
