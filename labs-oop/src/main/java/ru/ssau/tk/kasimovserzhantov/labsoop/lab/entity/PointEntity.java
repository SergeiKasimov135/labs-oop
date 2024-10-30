package ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "labs.t_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "function_id", nullable = false)
    private MathFunctionEntity functionEntity;

    @Column(name = "c_x_value")
    private Double xValue;

    @Column(name = "c_y_value")
    private Double yValue;

}
