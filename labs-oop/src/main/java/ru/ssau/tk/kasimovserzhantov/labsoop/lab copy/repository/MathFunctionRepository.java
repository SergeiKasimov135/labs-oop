package ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;

import java.util.List;

@Repository
public interface MathFunctionRepository extends JpaRepository<MathFunctionEntity, Integer> {

    List<MathFunctionEntity> findByFunctionTypeLikeIgnoreCase(String functionType);

}
