package ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CollectionIdJdbcTypeCode;

@Entity
@Table(schema = "labs", name = "t_lab_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "c_username")
    @NotNull
    @NotBlank
    private String username;

    @Column(name = "c_password")
    @NotNull
    @NotBlank
    @Size(min = 8, max = 255)
    private String password;

    @Column(name = "c_role")
    @Enumerated(EnumType.STRING)
    @NotNull
    private UserRole role;

}
