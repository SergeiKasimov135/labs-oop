package ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(scripts = {"/sql/1.0-SNAPSHOT__Basic_test_schema.sql", "/sql/points.sql"})
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PointRepositoryIT {

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private MathFunctionRepository mathFunctionRepository;

    @BeforeEach
    public void setup() {
        MathFunctionEntity functionEntity = new MathFunctionEntity();
        functionEntity.setId(1);
        functionEntity.setFunctionType("example_function");
        functionEntity.setCount(10);
        functionEntity.setXFrom(0D);
        functionEntity.setXTo(10D);
        mathFunctionRepository.save(functionEntity);

        PointEntity pointEntity = new PointEntity();
        pointEntity.setId(1);
        pointEntity.setFunctionEntity(functionEntity);
        pointEntity.setXValue(0D);
        pointEntity.setYValue(0D);
        pointRepository.save(pointEntity);
    }

    @Test
    public void testFindByFunctionEntity_ReturnsFilteredPointsList() {
        // given
        MathFunctionEntity functionEntity = mathFunctionRepository.findById(1).orElseThrow();

        // when
        List<PointEntity> points = pointRepository.findByFunctionEntity(functionEntity);

        // then
        assertEquals(1, points.size());
        assertEquals(1, points.get(0).getId());
    }
}