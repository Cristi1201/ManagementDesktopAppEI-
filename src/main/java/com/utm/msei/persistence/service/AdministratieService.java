package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.AdministratieDto;
import com.utm.msei.persistence.mapper.AdministratieMapper;
import com.utm.msei.persistence.repository.AdministratieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdministratieService {

    private final AdministratieRepository administratieRepository;

    private final AdministratieMapper administratieMapper;

    @Autowired
    public AdministratieService(AdministratieRepository administratieRepository, AdministratieMapper administratieMapper) {
        this.administratieRepository = administratieRepository;
        this.administratieMapper = administratieMapper;
    }

    @Transactional
    public AdministratieDto save(AdministratieDto administratieDto) {
        return administratieMapper.toDto(administratieRepository.save(administratieMapper.toEntity(administratieDto)));
    }

    @Transactional
    public void delete(int idAdmin) {
        if (administratieRepository.existsById(idAdmin)) {
            administratieRepository.deleteById(idAdmin);
        } else {
            throw new IllegalStateException("no user");
        }
    }

    public void updateUser(AdministratieDto administratieDto) {
        administratieRepository.updateIdUserBy(administratieMapper.toEntity(administratieDto).getIdUser());
    }

    @Transactional
    public AdministratieDto findByUserId(int id) {
        return administratieMapper.toDto(administratieRepository.findByIdUser_Id(id));
    }
}
