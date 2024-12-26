package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.PointEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.MathFunctionRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MathFunctionService {

    private final MathFunctionRepository mathFunctionRepository;

    private final MathFunctionMapper mathFunctionMapper;

    private final PointMapper pointMapper;

    @EntityGraph(attributePaths = "points")
    public List<MathFunctionDTO> findAllFunctions(String functionType) {
        if (functionType != null && !functionType.isBlank()) {
            return this.mathFunctionRepository.findByFunctionTypeLikeIgnoreCase(functionType)
                    .stream()
                    .map(mathFunctionMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            return this.mathFunctionRepository.findAll()
                    .stream()
                    .map(mathFunctionMapper::toDTO)
                    .collect(Collectors.toList());
        }
    }

    @Transactional
    public MathFunctionDTO create(MathFunctionDTO functionDTO) {
        MathFunctionEntity function = mathFunctionMapper.toEntity(functionDTO);

        if (functionDTO.getPoints() != null) {
            List<PointEntity> points = functionDTO.getPoints().stream()
                    .map(pointDTO -> pointMapper.toEntityWithFunction(pointDTO, function))
                    .collect(Collectors.toList());
            function.setPoints(points);
        }

        MathFunctionEntity savedFunction = mathFunctionRepository.save(function);
        return mathFunctionMapper.toDTO(savedFunction);
    }

    public MathFunctionDTO read(int id) {
        return this.mathFunctionRepository
                .findById(id)
                .map(mathFunctionMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public MathFunctionDTO update(MathFunctionDTO functionDTO) {
        MathFunctionEntity existingEntity = mathFunctionRepository.findById(functionDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Function not found with ID: " + functionDTO.getId()));

        if (functionDTO.getFunctionType() != null) {
            existingEntity.setFunctionType(functionDTO.getFunctionType());
        }
        if (functionDTO.getXFrom() != null) {
            existingEntity.setXFrom(functionDTO.getXFrom());
        }
        if (functionDTO.getXTo() != null) {
            existingEntity.setXTo(functionDTO.getXTo());
        }

        if (functionDTO.getPoints() != null) {
            existingEntity.getPoints().clear();
            existingEntity.getPoints().addAll(
                    functionDTO.getPoints().stream()
                            .map(pointDTO -> mathFunctionMapper.toEntityWithFunction(pointDTO, existingEntity))
                            .toList()
            );
        }

        MathFunctionEntity updatedEntity = mathFunctionRepository.save(existingEntity);
        return mathFunctionMapper.toDTO(updatedEntity);
    }

    public void delete(int id) {
        MathFunctionEntity functionEntity = mathFunctionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Function not found with ID: " + id));

        mathFunctionRepository.delete(functionEntity);
    }

}
