package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.core.FunctionRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/tabulated-function-mathfunc")
public class TabulatedFunctionMathController {

    private final FunctionRepository functionRepository;

    public TabulatedFunctionFactory tableFunctionFactory;

    @GetMapping("/modal")
    public String showModalForm(@RequestParam("redirectTarget") String redirectTarget,
                                @RequestParam("target") String target, Model model, HttpSession session) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if(session.getAttribute("FACTORY_KEY") != null) {
            tableFunctionFactory = (TabulatedFunctionFactory) session.getAttribute("FACTORY_KEY");
        }
        else{
            tableFunctionFactory = new ArrayTabulatedFunctionFactory();
        }

        model.addAttribute("factory", tableFunctionFactory.getClass().getSimpleName());

        Map<String, MathFunction> mapfunc = functionRepository.getFunctionMap();

        model.addAttribute("functionMap",mapfunc);
        model.addAttribute("redirectTarget", redirectTarget);
        model.addAttribute("target", target);
        return "fragments/modalFormMathFunc";
    }

    @PostMapping("/createModal")
    public String createModalFunction(@RequestParam("function") String functionName,
                                      @RequestParam("xFrom") double xFrom ,
                                      @RequestParam("xTo") double xTo,
                                      @RequestParam("count") int count,
                                      @RequestParam("redirectTarget") String redirectTarget,
                                      @RequestParam("target") String target,
                                      Model model, HttpSession session){

        MathFunction function = functionRepository.getFunction(functionName);

        TabulatedFunction func = tableFunctionFactory.create(function,xFrom,xTo,count);
        session.setAttribute(target+"Func",func);

        return "redirect:/"+redirectTarget;
    }

}
