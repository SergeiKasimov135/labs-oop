package ru.ssau.tk.kasimovserzhantov.labsoop.lab.core;

import org.springframework.stereotype.Component;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class FunctionRepository {

    private final List<AnnotatedFunction> staticFunctions = new ArrayList<>();

    private final List<AnnotatedFunction> userFunctions = new ArrayList<>();

    public FunctionRepository() throws InvocationTargetException, NoSuchMethodException,
            InstantiationException, IllegalAccessException {
        staticFunctions.addAll(MathFunctionProvider.loadFunctions("functions"));
    }

    public void addUserFunction(AnnotatedFunction function) {
        userFunctions.add(function);
    }

    public MathFunction getFunction(String name) {
        for (AnnotatedFunction function : staticFunctions) {
            if (function.getLocalizedName().equals(name)) {
                return function.getFunction();
            }
        }

        // Проверяем пользовательские функции
        for (AnnotatedFunction function : userFunctions) {
            if (function.getLocalizedName().equals(name)) {
                return function.getFunction();
            }
        }

        return null;
    }

    public List<AnnotatedFunction> getAllFunctions() {
        List<AnnotatedFunction> allFunctions = new ArrayList<>();
        allFunctions.addAll(staticFunctions);
        allFunctions.addAll(userFunctions);
        return allFunctions;
    }

    public Map<String, MathFunction> getFunctionMap() {
        List<AnnotatedFunction> allFunctions = new ArrayList<>();
        allFunctions.addAll(staticFunctions);
        allFunctions.addAll(userFunctions);

        return allFunctions.stream()
                .sorted(Comparator.comparingInt(AnnotatedFunction::getPriority)
                        .thenComparing(AnnotatedFunction::getLocalizedName))
                .collect(Collectors.toMap(
                        AnnotatedFunction::getLocalizedName,   // Ключ: локализованное имя функции
                        AnnotatedFunction::getFunction,        // Значение: сама функция
                        (existing, replacement) -> existing,   // Если два элемента с одинаковым ключом — оставить первый
                        LinkedHashMap::new));                  // Сохраняем порядок сортировки
    }

}
