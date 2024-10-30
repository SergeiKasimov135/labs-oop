package ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;

import java.util.List;

public interface PointRepository extends JpaRepository<PointEntity, Integer> {
    List<PointEntity> findByFunctionEntity(MathFunctionEntity functionEntity);
}
