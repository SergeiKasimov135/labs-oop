package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.LinkedListTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.operations.implementations.TabulatedDifferentialOperator;

import java.io.*;
import java.nio.file.Paths;

public class LinkedListTabulatedFunctionSerialization {

    public static void main(String[] args) {
        String filePath = Paths.get("labs-oop/output", "serialized linked list functions.bin").toString();

        TabulatedFunction linkedListTabulatedFunction =
                new LinkedListTabulatedFunction(new double[]{1.7, 2.8, 6.5}, new double[]{5.0, 8.3, 9.19});

        TabulatedDifferentialOperator differentialOperator = new TabulatedDifferentialOperator();
        TabulatedFunction firstDerivative = differentialOperator.derive(linkedListTabulatedFunction);
        TabulatedFunction secondDerivative = differentialOperator.derive(firstDerivative);

        try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            FunctionsIO.serialize(bufferedOutputStream, linkedListTabulatedFunction);
            FunctionsIO.serialize(bufferedOutputStream, firstDerivative);
            FunctionsIO.serialize(bufferedOutputStream, secondDerivative);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            TabulatedFunction deserializedLinkedListTabulatedFunction = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedFirstDerivative = FunctionsIO.deserialize(bufferedInputStream);
            TabulatedFunction deserializedSecondDerivative = FunctionsIO.deserialize(bufferedInputStream);

            System.out.println("Исходная функция: " + deserializedLinkedListTabulatedFunction.toString());
            System.out.println("Первая производная исходной функции: " + deserializedFirstDerivative.toString());
            System.out.println("Вторая производная исходной функции: " + deserializedSecondDerivative.toString());
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
