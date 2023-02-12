package com.utm.msei.persistence.service;

import com.utm.msei.persistence.dto.NoteDto;
import com.utm.msei.persistence.dto.OrarDto;
import com.utm.msei.persistence.mapper.NoteMapper;
import com.utm.msei.persistence.mapper.OrarMapper;
import com.utm.msei.persistence.repository.NoteRepository;
import com.utm.msei.persistence.repository.OrarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    private final NoteMapper noteMapper;

    @Autowired
    public NoteService(NoteRepository noteRepository, NoteMapper noteMapper) {
        this.noteRepository = noteRepository;
        this.noteMapper = noteMapper;
    }

    @Transactional
    public NoteDto save(NoteDto noteDto) {
        return noteMapper.toDto(noteRepository.save(noteMapper.toEntity(noteDto)));
    }
}
