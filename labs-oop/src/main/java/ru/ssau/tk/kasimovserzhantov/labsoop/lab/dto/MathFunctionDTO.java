package ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MathFunctionDTO {

    private int id;

    private String functionType;

    private int count;

    private double xFrom;

    private double xTo;

    private List<PointDTO> points;

}
