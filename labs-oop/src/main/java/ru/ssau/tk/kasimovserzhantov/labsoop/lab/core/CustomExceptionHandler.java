package ru.ssau.tk.kasimovserzhantov.labsoop.lab.core;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import org.springframework.web.bind.annotation.ControllerAdvice;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ArrayIsNotSortedException.class)
    public String handleArrayIsNotSortedException(ArrayIsNotSortedException exception, Model model,
                                                  HttpServletRequest request, RedirectAttributes redirectAttributes) {

        String page = "/";
        String redirectTarget = request.getParameter("redirectTarget");

        redirectAttributes.addAttribute("errorMessage", "Массив не отсортирован");
        redirectAttributes.addAttribute("showError", true);
        redirectAttributes.addAttribute("redirectTarget", redirectTarget);
        return "redirect:/"+redirectTarget;
    }

    @ExceptionHandler(DifferentLengthOfArraysException.class)
    public String handleDifferentLengthOfArraysException(DifferentLengthOfArraysException exception,
                                                         Model model, HttpServletRequest request) {

        String page = "/";
        if(Objects.equals(request.getRequestURI(), "/tabulated-function-array/create")){
            page = "tabulated-function-array";
        }

        model.addAttribute("errorTitle","Ошибка");
        model.addAttribute("errorMessage",exception.getMessage());

        return request.getHeader("Referer");
    }

    @ExceptionHandler(LoadFunctionException.class)
    public ResponseEntity<Map<String, String>> handleLoadFunctionExecption(LoadFunctionException exception,
                                                                           Model model, HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("error", exception.getMessage());
        response.put("showError", "true");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InconsistentFunctionsException.class)
    public String handleInconsistentFunctionsException(InconsistentFunctionsException exception, Model model,
                                                       HttpServletRequest request,
                                                       RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("errorMessage", exception.getMessage());
        redirectAttributes.addAttribute("showError", true);

        return "redirect:/tabulated-operations";
    }

    @ExceptionHandler(RemoveIncorrectPoint.class)
    public ResponseEntity<Map<String, String>> handleRemoveIncorrectPoint(RemoveIncorrectPoint exception,
                                                                          Model model,
                                                                          HttpServletRequest request,
                                                                          RedirectAttributes redirectAttributes) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Некорректная точка");
        response.put("showError", "true");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
