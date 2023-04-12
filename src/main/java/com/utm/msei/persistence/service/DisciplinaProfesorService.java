package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.DisciplinaDto;
import com.utm.msei.persistence.dto.DisciplinaProfesorDto;
import com.utm.msei.persistence.dto.ProfesorDto;
import com.utm.msei.persistence.entity.DisciplinaProfesorEntity;
import com.utm.msei.persistence.mapper.DisciplinaMapper;
import com.utm.msei.persistence.mapper.DisciplinaProfesorMapper;
import com.utm.msei.persistence.mapper.ProfesorMapper;
import com.utm.msei.persistence.repository.DisciplinaProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DisciplinaProfesorService {

    private final DisciplinaProfesorRepository disciplinaProfesorRepository;

    private final DisciplinaProfesorMapper disciplinaProfesorMapper;
    private final ProfesorMapper profesorMapper;
    private final DisciplinaMapper disciplinaMapper;

    @Autowired
    public DisciplinaProfesorService(DisciplinaProfesorRepository disciplinaProfesorRepository, DisciplinaProfesorMapper disciplinaProfesorMapper, ProfesorMapper profesorMapper, DisciplinaMapper disciplinaMapper) {
        this.disciplinaProfesorRepository = disciplinaProfesorRepository;
        this.disciplinaProfesorMapper = disciplinaProfesorMapper;
        this.profesorMapper = profesorMapper;
        this.disciplinaMapper = disciplinaMapper;
    }

    @Transactional
    public DisciplinaProfesorDto save(DisciplinaProfesorDto disciplinaProfesorDto) {
        return disciplinaProfesorMapper.toDto(disciplinaProfesorRepository.save(disciplinaProfesorMapper.toEntity(disciplinaProfesorDto)));
    }

    @Transactional
    public List<DisciplinaProfesorDto> getAll() {
        List<DisciplinaProfesorDto> dpDto = new ArrayList<>();
        List<DisciplinaProfesorEntity> dpEntity = disciplinaProfesorRepository.findAll();
        dpEntity.forEach(dp -> dpDto.add(disciplinaProfesorMapper.toDto(dp)));
        return dpDto;
    }

    /**
     * @return all "profs" with their "disciplines"
     */
    public Map<ProfesorDto, List<DisciplinaDto>> getProfDiscList() {
        List<DisciplinaProfesorDto> oneToOneProfDisc = this.getAll();
        Collections.sort(oneToOneProfDisc, new Comparator<DisciplinaProfesorDto>(   ) {
            @Override
            public int compare(DisciplinaProfesorDto o1, DisciplinaProfesorDto o2) {
                return o1.getIdProfesor().getIdUser().getNume().compareTo(o2.getIdProfesor().getIdUser().getNume());
            }
        });
        Map<ProfesorDto, List<DisciplinaDto>> profDiscsMap = new LinkedHashMap<>();

        for (int i = 0; i < oneToOneProfDisc.size(); i ++) {
            boolean keyExists = false;
            for (Map.Entry<ProfesorDto, List<DisciplinaDto>> entry : profDiscsMap.entrySet()) {
                if (entry.getKey().getId() == oneToOneProfDisc.get(i).getIdProfesor().getId()) {
                    keyExists = true;
                    entry.getValue().add(oneToOneProfDisc.get(i).getIdDisciplina());
                }
            }
            if (!keyExists) {
                profDiscsMap.put(oneToOneProfDisc.get(i).getIdProfesor(), new ArrayList<>(List.of(oneToOneProfDisc.get(i).getIdDisciplina())));
            }
        }
        return profDiscsMap;
    }

    @Transactional
    public void updateDiscsForProf(ProfesorDto profesorDto, List<DisciplinaDto> discipline) {
        // TODO 100 salience
        // TODO review this output
        List<DisciplinaProfesorDto> disciplinaDtosList = disciplinaProfesorRepository.findAllDiscByIdProfesor(profesorMapper.toEntity(profesorDto)).stream()
                .map(s -> disciplinaProfesorMapper.toDto(s)).collect(Collectors.toList());

        List<DisciplinaDto> disciplinaOfProfesorList = disciplinaDtosList.stream().map(DisciplinaProfesorDto::getIdDisciplina).collect(Collectors.toList());
        disciplinaOfProfesorList.forEach(discOfProf -> {
            if (!discipline.contains(discOfProf)) {
                disciplinaProfesorRepository.deleteByIdProfesorAndIdDisciplina(profesorMapper.toEntity(profesorDto), disciplinaMapper.toEntity(discOfProf));
            } else {
                discipline.remove(discOfProf);
            }
        });
        discipline.forEach(disciplinaDto -> {
            DisciplinaProfesorDto disciplinaProfesorDto = new DisciplinaProfesorDto(profesorDto, disciplinaDto);
            disciplinaProfesorRepository.save(disciplinaProfesorMapper.toEntity(disciplinaProfesorDto));
        });
    }

    @Transactional
    public void deleteWhereDisc(DisciplinaDto disciplinaDto) {
        disciplinaProfesorRepository.deleteByIdDisciplina(disciplinaMapper.toEntity(disciplinaDto));
    }
}










