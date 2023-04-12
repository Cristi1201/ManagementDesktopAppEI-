package com.utm.msei.persistence.service;

import com.utm.msei.controllers.administration.actions.OrarHandler;
import com.utm.msei.persistence.dto.*;
import com.utm.msei.persistence.mapper.*;
import com.utm.msei.persistence.repository.OrarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrarService {

    private final OrarRepository orarRepository;

    private final OrarMapper orarMapper;
    private final ZiSaptamanaMapper ziSaptamanaMapper;
    private final DurataLectieMapper durataLectieMapper;
    private final ProfesorMapper profesorMapper;
    private final DisciplinaProfesorMapper disciplinaProfesorMapper;
    private final ClasaMapper clasaMapper;

    @Autowired
    public OrarService(OrarRepository orarRepository, OrarMapper orarMapper, ZiSaptamanaMapper ziSaptamanaMapper, DurataLectieMapper durataLectieMapper,
                       ProfesorMapper profesorMapper, DisciplinaProfesorMapper disciplinaProfesorMapper,
                       ClasaMapper clasaMapper) {
        this.orarRepository = orarRepository;
        this.orarMapper = orarMapper;
        this.ziSaptamanaMapper = ziSaptamanaMapper;
        this.durataLectieMapper = durataLectieMapper;
        this.profesorMapper = profesorMapper;
        this.disciplinaProfesorMapper = disciplinaProfesorMapper;
        this.clasaMapper = clasaMapper;
    }

    @Transactional
    public OrarDto save(OrarDto orarDto) {
        return orarMapper.toDto(orarRepository.save(orarMapper.toEntity(orarDto)));
    }

    @Transactional
    public List<OrarDto> getForClass(String clasaName) {
        List<OrarDto> orarDtoList = orarRepository.findByIdClasa_Nume(clasaName).stream().map(orarMapper::toDto).collect(Collectors.toList());
        return orarDtoList;
    }

    @Transactional
    public OrarDto checkAvailabilityOfProfForDay(OrarHandler.OrarTable orarRow, ProfesorDto choosedProfesorDto) {
        return orarMapper.toDto(orarRepository.findIfProfIsOcupied(ziSaptamanaMapper.toEntity(orarRow.getZiDto()), durataLectieMapper.toEntity(orarRow.getDurataDto()), profesorMapper.toEntity(choosedProfesorDto)));
    }

    @Transactional
    public void delete(int id) {
        orarRepository.deleteById(id);
    }

    @Transactional
    public int update(ClasaDto clasaDto, ZiSaptamanaDto ziSaptamanaDto, DurataLectieDto durataLectieDto, DisciplinaProfesorDto disciplinaProfesorDto) {
        return orarRepository.updateDiscProfByClasaZiDurata(disciplinaProfesorMapper.toEntity(disciplinaProfesorDto), clasaMapper.toEntity(clasaDto), ziSaptamanaMapper.toEntity(ziSaptamanaDto), durataLectieMapper.toEntity(durataLectieDto));
    }
}
