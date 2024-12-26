package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.factory.TabulatedFunctionFactory;

@Controller
public class SettingsController {

    @PostMapping("/settings")
    public String updateSettings(@RequestParam("factoryType") String factoryType, HttpSession session) {

        TabulatedFunctionFactory factory = null;

        if ("array".equals(factoryType)) {
            factory = new ArrayTabulatedFunctionFactory();
        } else if ("linkedlist".equals(factoryType)) {
            factory = new LinkedListTabulatedFunctionFactory();
        }

        session.setAttribute("FACTORY_KEY", factory);

        return "home";
    }

}
