package ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "labs.t_function")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MathFunctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "c_function_type")
    @NotNull
    private String functionType;

    @Column(name = "c_count")
    private int count;

    @Column(name = "c_x_from")
    private Double xFrom;

    @Column(name = "c_x_to")
    private Double xTo;

    @OneToMany(mappedBy = "functionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PointEntity> points;

}
