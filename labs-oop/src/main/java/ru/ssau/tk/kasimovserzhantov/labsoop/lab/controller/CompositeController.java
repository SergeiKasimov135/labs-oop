package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.core.AnnotatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.core.FunctionRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.core.MathFunctionProvider;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.MathFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.CompositeFunction;

@Controller
@RequestMapping("/composite")
public class CompositeController {

    private final FunctionRepository functionRepository;

    public CompositeController(FunctionRepository functionRepository) {
        this.functionRepository = functionRepository;
    }

    @GetMapping
    public String CompositeController(Model model) {
        model.addAttribute("functions", MathFunctionProvider.mathFunctions());
        return "composite";
    }

    @PostMapping("/create")
    public String CompositeControllerPost(@RequestParam String firstFunc,
                                          @RequestParam String secondFunc,
                                          @RequestParam String compositeName) {

        MathFunction first = this.functionRepository.getFunction(firstFunc);
        MathFunction second = this.functionRepository.getFunction(secondFunc);

        AnnotatedFunction function = new AnnotatedFunction(new CompositeFunction(first,second), 0, compositeName);

        this.functionRepository.addUserFunction(function);

        return "redirect:/composite";
    }

}
