package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;

import java.util.Objects;

@Controller
@RequestMapping("/arrayFunc")
public class CreateFunctionArrayOrLinkedModalController {

    @GetMapping("/createForm")
    public String createForm(@RequestParam String target, @RequestParam("redirectTarget") String redirectTarget,
                             Model model, HttpSession session) {
        model.addAttribute("target", target);
        model.addAttribute("redirectTarget", redirectTarget);
        model.addAttribute("factory", session.getAttribute("FACTORY_KEY").getClass().getSimpleName());
        return "fragments/createForm";
    }

    @PostMapping("/generateTable")
    public String generateTable(@RequestParam int points, @RequestParam String target,
                                @RequestParam("redirectTarget") String redirectTarget, Model model) {
        model.addAttribute("points", points);
        model.addAttribute("target", target);
        model.addAttribute("redirectTarget", redirectTarget);
        return "fragments/tableForm";
    }

    @PostMapping("/createFunction")
    public String createFunction(@RequestParam String target, @RequestParam("redirectTarget") String redirectTarget,
                                 @RequestParam double[] x,@RequestParam double[] y, Model model, HttpSession session) {

        TabulatedFunctionFactory factory = (TabulatedFunctionFactory) session.getAttribute("FACTORY_KEY");

        TabulatedFunction function = factory.create(x, y);

        model.addAttribute("function", function);

        if (Objects.equals(target, "operand1")) {
            session.setAttribute("operand1Func", function);
        } else if (Objects.equals(target, "operand2")) {
            session.setAttribute("operand2Func", function);
        } else if (Objects.equals(target, "diff")) {
            session.setAttribute("diffFunc", function);
        }else if(Objects.equals(target, "integral")) {
            session.setAttribute("integralFunc", function);
        }else if(Objects.equals(target, "graph")) {
            session.setAttribute("graphFunc", function);
        }

        return "redirect:/"+redirectTarget;
    }
}