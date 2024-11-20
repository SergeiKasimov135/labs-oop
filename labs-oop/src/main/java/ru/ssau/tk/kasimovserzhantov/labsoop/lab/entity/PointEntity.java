package ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(schema = "labs", name = "t_point")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "functionEntity")
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "c_x_value")
    private double xValue;

    @Column(name = "c_y_value")
    private double yValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "function_id", nullable = false)
    @JsonBackReference
    private MathFunctionEntity functionEntity;

}
