package ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;

import java.util.List;

public interface MathFunctionRepository extends JpaRepository<MathFunctionEntity, Integer> {
    List<MathFunctionEntity> findByFunctionType(String functionType);
}
