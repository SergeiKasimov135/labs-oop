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

    public List<PointDTO> findByFunctionEntity(int functionId) {
        MathFunctionEntity function = this.mathFunctionRepository.findById(functionId).orElse(null);
        if (function == null) {
            return null;
        }

        return this.pointRepository.findByFunctionEntity(function)
                .stream()
                .map(PointMapper::pointEntityToDTO)
                .collect(Collectors.toList());
    }

    public PointDTO create(PointDTO pointDTO) {
        PointEntity point = PointMapper.pointDTOToPointEntity(pointDTO);
        PointEntity newPoint = this.pointRepository.save(point);

        return PointMapper.pointEntityToDTO(newPoint);
    }

    public PointDTO read(int id) {
        return this.pointRepository.findById(id)
                .map(PointMapper::pointEntityToDTO)
                .orElse(null);
    }

    public PointDTO update(PointDTO pointDTO) {
        PointEntity point = PointMapper.pointDTOToPointEntity(pointDTO);
        PointEntity editedPoint = this.pointRepository.save(point);

        return PointMapper.pointEntityToDTO(editedPoint);
    }

    public void delete(int id) {
        this.pointRepository.deleteById(id);
    }

}
