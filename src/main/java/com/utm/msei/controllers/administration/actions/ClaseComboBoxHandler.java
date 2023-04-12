package com.utm.msei.controllers.administration.actions;

import com.utm.msei.persistence.dto.ClasaDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

import static com.utm.msei.Main.serviceHandler;

@Data
public class ClaseComboBoxHandler {
    private int id;
    private String name;
    private ClasaDto clasaDto;
    private List<ClasaDto> claseDtos;

    public ClaseComboBoxHandler() {
        this.claseDtos = serviceHandler.getClasaService().getAll();
    }

    private ClaseComboBoxHandler(ClasaDto clasaDto) {
        this.id = clasaDto.getId();
        this.name = clasaDto.getNume();
        this.clasaDto = clasaDto;
    }

    public List<ClaseComboBoxHandler> getAllClase() {
        return this.claseDtos.stream().map(c -> new ClaseComboBoxHandler(c)).collect(Collectors.toList());
    }
}
