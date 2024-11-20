package ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(schema = "labs", name = "t_function")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "points")
public class MathFunctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "c_function_type")
    @NotNull
    private String functionType;

    @Column(name = "c_count")
    @Min(2)
    private Integer count;

    @Column(name = "c_x_from")
    private Double xFrom;

    @Column(name = "c_x_to")
    private Double xTo;

    @OneToMany(
            mappedBy = "functionEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<PointEntity> points;

}
