package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.PointDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.MathFunctionRepository;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.PointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    private final MathFunctionRepository mathFunctionRepository;

    private final PointMapper pointMapper;

    public List<PointDTO> findAllPoints(int functionId) {
        MathFunctionEntity function = this.mathFunctionRepository.findById(functionId).orElse(null);
        if (function == null) {
            return null;
        }

        return this.pointRepository.findByFunctionEntity(function)
                .stream()
                .map(pointMapper::toDTO)
                .collect(Collectors.toList());
    }

    public PointDTO create(PointDTO pointDTO) {
        MathFunctionEntity functionEntity = mathFunctionRepository.findById(pointDTO.getFunctionId())
                .orElseThrow(() -> new IllegalArgumentException("Function with ID " + pointDTO.getFunctionId() + " not found"));

        functionEntity.setCount(functionEntity.getCount() + 1);
        mathFunctionRepository.save(functionEntity);

        PointEntity point = pointMapper.toEntityWithFunction(pointDTO, functionEntity);
        PointEntity newPoint = pointRepository.save(point);

        return pointMapper.toDTO(newPoint);
    }

    public PointDTO read(int id) {
        return this.pointRepository.findById(id)
                .map(pointMapper::toDTO)
                .orElse(null);
    }

    public PointDTO update(PointDTO pointDTO) {
        PointEntity existingPoint = pointRepository.findById(pointDTO.getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("Point with ID " + pointDTO.getId() + " not found"));

        existingPoint.setXValue(pointDTO.getXValue());
        existingPoint.setYValue(pointDTO.getYValue());

        PointEntity editedPoint = this.pointRepository.save(existingPoint);

        return pointMapper.toDTO(editedPoint);
    }

    public void delete(int id) {
        this.pointRepository.deleteById(id);
    }

}
