package ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LabUserDTO {

    private String username;
    private String password;
    private UserRole role;

}
