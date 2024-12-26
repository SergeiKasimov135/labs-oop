package ru.ssau.tk.kasimovserzhantov.labsoop.lab.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.PointDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.exceptions.LoadFunctionException;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.coredefenitions.interfaces.TabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.functions.implementations.ArrayTabulatedFunction;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.MathFunctionRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.service.MathFunctionService;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.service.PointService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/memory")
public class LoadAndSaveControllers {

    @Autowired
    public MathFunctionRepository mathFunctionRepository;

    private final MathFunctionService mathFunctionService;
    private final PointService pointService;


    @PostMapping("/load")
    public String load(@RequestParam("target") String target, @RequestParam("id") Integer id, Model model, HttpSession session) {
        System.out.println("Loading " + target);

        System.out.println(target);

        MathFunctionEntity loadFunc = this.mathFunctionRepository.findById(id).orElse(null);

        List<PointEntity> list = loadFunc.getPoints();
        if(list.size() >= 2){
            double[] x = new double[list.size()];
            double[] y = new double[list.size()];

            int i = 0;
            for(PointEntity point : list) {
                System.out.println(point.getId());
                x[i] = point.getXValue();
                System.out.println(point.getXValue());
                y[i] = point.getYValue();
                System.out.println(point.getYValue());
                i++;
            }

            TabulatedFunction result = new ArrayTabulatedFunction(x,y);
            session.setAttribute(target+"Func", result);
            return "redirect:/tabulated-operations";
        }else{
            throw new LoadFunctionException("У функции должно быть >2 точек");
        }
    }

    @PostMapping("/save")
    public String save(@RequestParam("target") String saveTarget, @RequestParam("funcName") String funcName, Model model, HttpSession session) {

        TabulatedFunction func = (TabulatedFunction) session.getAttribute(saveTarget+"Func");
        if(func == null) {
            throw new IllegalArgumentException("Function is empty");
        }

        MathFunctionDTO dto = new MathFunctionDTO();
        dto.setFunctionType(funcName);
        dto.setXTo(func.rightBound());
        dto.setXFrom(func.leftBound());
        dto.setCount(func.getCount());

        Integer idResult = mathFunctionService.create(dto).getId();

        for(int i = 0; i < func.getCount(); i++) {
            PointDTO point = new PointDTO();
            point.setFunctionId(idResult);
            point.setXValue(func.getX(i));
            point.setYValue(func.getY(i));

            pointService.create(point);
        }

        return "redirect:/tabulated-operations";
    }

}