package ru.ssau.tk.kasimovserzhantov.labsoop.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.dto.MathFunctionDTO;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.entity.MathFunctionEntity;
import ru.ssau.tk.kasimovserzhantov.labsoop.lab.repository.MathFunctionRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MathFunctionService {

    private final MathFunctionRepository mathFunctionRepository;

    public List<MathFunctionDTO> getByFunctionType(String functionType) {
        return this.mathFunctionRepository.findByFunctionType(functionType)
                .stream()
                .map(MathFunctionMapper::functionEntityToDTO)
                .collect(Collectors.toList());
    }

    public MathFunctionDTO create(MathFunctionDTO functionDTO) {
        MathFunctionEntity functionEntity = MathFunctionMapper.functionDTOToFunctionEntity(functionDTO);
        MathFunctionEntity newFunction = this.mathFunctionRepository.save(functionEntity);

        return MathFunctionMapper.functionEntityToDTO(newFunction);
    }

    public MathFunctionDTO read(int id) {
        return this.mathFunctionRepository
                .findById(id)
                .map(MathFunctionMapper::functionEntityToDTO)
                .orElse(null);
    }

    public MathFunctionDTO update(MathFunctionDTO functionDTO) {
        MathFunctionEntity functionEntity = MathFunctionMapper.functionDTOToFunctionEntity(functionDTO);
        MathFunctionEntity editedFunction = this.mathFunctionRepository.save(functionEntity);

        return MathFunctionMapper.functionEntityToDTO(editedFunction);
    }

    public void delete(int id) {
        this.mathFunctionRepository.deleteById(id);
    }

}
