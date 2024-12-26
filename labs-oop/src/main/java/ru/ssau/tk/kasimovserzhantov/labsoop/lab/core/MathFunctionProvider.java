package ru.ssau.tk.kasimovserzhantov.labsoop.lab.core;

import org.reflections.Reflections;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.FunctionInfo;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ConstantFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.IdentityFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.SqrFunction;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class MathFunctionProvider {

    public static Map<String, MathFunction> mathFunctions() {
        Map<String, MathFunction> mathFunctions = new TreeMap<>();

        mathFunctions.put("Тождественная функция", new IdentityFunction());
        mathFunctions.put("Константная функция", new ConstantFunction());
        mathFunctions.put("Квадратичная функция", new SqrFunction());

        return mathFunctions;
    }

    public static List<AnnotatedFunction> loadFunctions(String packageName)
            throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        List<AnnotatedFunction> functions = new ArrayList<>();

        try {
            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .setUrls(ClasspathHelper.forPackage(packageName))
                    .setScanners(new TypeAnnotationsScanner()));

            Set<Class<?>> annotatedClasses = reflections.getTypesAnnotatedWith(FunctionInfo.class);

            for (Class<?> clazz : annotatedClasses) {
                if (MathFunction.class.isAssignableFrom(clazz)) {
                    FunctionInfo info = clazz.getAnnotation(FunctionInfo.class);
                    MathFunction function = (MathFunction) clazz.getDeclaredConstructor().newInstance();
                    AnnotatedFunction newFunc = new AnnotatedFunction(function, info.priority(), info.name());
                    functions.add(newFunc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return functions;
    }

}
