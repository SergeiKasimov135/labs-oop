package ru.ssau.tk.kasimovserzhantov.labsoop.lab.io;

import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.Point;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;

import java.io.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class FunctionsIO {

    private FunctionsIO() {
        throw new UnsupportedOperationException();
    }

    public static void writeTabulatedFunction(BufferedWriter writer, TabulatedFunction function) {
        try (PrintWriter printWriter = new PrintWriter(writer)) {
            printWriter.println(function.getCount());
            for (Point point : function) {
                printWriter.printf("%f %f%n", point.getX(), point.getY());
            }

            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void writeTabulatedFunction(BufferedOutputStream outputStream, TabulatedFunction function)
            throws IOException {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            dataOutputStream.writeInt(function.getCount());
            for (Point point : function) {
                dataOutputStream.writeDouble(point.getX());
                dataOutputStream.writeDouble(point.getY());
            }

            dataOutputStream.flush();
        }
    }

    public static TabulatedFunction readTabulatedFunction(BufferedReader reader, TabulatedFunctionFactory factory)
            throws IOException {
        String line = reader.readLine();
        int count = Integer.parseInt(line.trim());

        double[] xValues = new double[count];
        double[] yValues = new double[count];

        NumberFormat numberFormat = NumberFormat.getInstance(Locale.forLanguageTag("ru"));
        for (int i = 0; i < count; ++i) {
            try {
                line = reader.readLine();
                String[] parts = line.split(" ");
                xValues[i] = numberFormat.parse(parts[0]).doubleValue();
                yValues[i] = numberFormat.parse(parts[1]).doubleValue();
            } catch (ParseException ex) {
                throw new IOException(ex);
            }
        }

        return factory.create(xValues, yValues);
    }

    public static TabulatedFunction readTabulatedFunction(BufferedInputStream inputStream, TabulatedFunctionFactory factory)
            throws IOException {
        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            int count = dataInputStream.readInt();

            double[] xValues = new double[count];
            double[] yValues = new double[count];
            for (int i = 0; i < count; ++i) {
                xValues[i] = dataInputStream.readDouble();
                yValues[i] = dataInputStream.readDouble();
            }

            return factory.create(xValues, yValues);
        }
    }

    public static void serialize(BufferedOutputStream stream, TabulatedFunction function) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(stream);
        objectOutputStream.writeObject(function);
        objectOutputStream.flush();
    }

    public static TabulatedFunction deserialize(BufferedInputStream stream) throws IOException, ClassNotFoundException {
        ObjectInputStream objectOutputStream = new ObjectInputStream(stream);
        return (TabulatedFunction) objectOutputStream.readObject();
    }

    public static void serializeJson(BufferedWriter writer, ArrayTabulatedFunction function) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(function);
        writer.write(json);

        writer.flush();
    }

    public static ArrayTabulatedFunction deserializeJson(BufferedReader reader) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        return mapper.readValue(reader, ArrayTabulatedFunction.class);
    }
}
