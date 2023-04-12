package com.utm.msei.controllers.administration.actions;

import com.utm.msei.persistence.dto.DisciplinaDto;
import com.utm.msei.persistence.dto.ProfesorDto;
import javafx.collections.ObservableList;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.stream.Collectors;

import static com.utm.msei.Main.serviceHandler;

public class DiscProfHandler {

    private Map<ProfesorDto, List<DisciplinaDto>> disciplinaProfesorDtos;
    private List<DiscProfTable> discProfTable;

    private List<DisciplinaDto> disciplineDto;
    private List<Discipline> discipline;

    public DiscProfHandler() {
        Map<ProfesorDto, List<DisciplinaDto>> disciplinaProfesorDtos = serviceHandler.getDisciplinaProfesorService().getProfDiscList();
        List<ProfesorDto> profesorDtos = serviceHandler.getProfesorService().getAll();
        discProfTable = new ArrayList<>();
        boolean profHasDisc = false;
        for(ProfesorDto profesorDto : profesorDtos) {
            profHasDisc = false;
            for (Map.Entry<ProfesorDto, List<DisciplinaDto>> entry : disciplinaProfesorDtos.entrySet()) {
                if (entry.getKey().getId() == profesorDto.getId()) {
                    profHasDisc = true;
                    discProfTable.add(new DiscProfTable(profesorDto, entry.getValue()));
                }
            }
            if (!profHasDisc) {
                discProfTable.add(new DiscProfTable(profesorDto, Collections.EMPTY_LIST));
            }
        }


        disciplineDto = serviceHandler.getDisciplinaService().getAll();
        discipline = disciplineDto.stream().map(disc -> new DiscProfHandler.Discipline(disc)).collect(Collectors.toList());
    }

    public List<DiscProfTable> getProfDiscs() {
        return this.discProfTable;
    }

    public List<Discipline> getAllDisicipline() {
        return this.discipline;
    }

    public void setDeletedDisc(Discipline disciplina) {
        for (DiscProfHandler.Discipline disc : this.discipline) {
            if (disc.getId() == disciplina.getId()) {
                disc.setDeleted();
                break;
            }
        }
    }

    public class DiscProfTable {
        @Getter
        @Setter
        private int profId;
        @Getter
        @Setter
        private ProfesorDto profesorDto;
        @Getter
        @Setter
        private String numeProf;
        @Getter
        @Setter
        private String prenumeProf;
        @Getter
        @Setter
        private String profesor;
        @Getter
        private List<DisciplinaDto> discipline;
        @Getter
        @Setter
        private List<String> disciplineName;
        private boolean wasModified = false;

        public DiscProfTable() {
            this.profId = 0;
        }

        public DiscProfTable(ProfesorDto profesorDto, List<DisciplinaDto> disciplinaDtos) {
            this.profesorDto = profesorDto;
            this.profId = profesorDto.getId();
            this.profesor = profesorDto.getIdUser().getNume() + " " + profesorDto.getIdUser().getPrenume();
            this.discipline = disciplinaDtos;
            this.disciplineName = disciplinaDtos.stream().map(d -> d.getDisciplina()).collect(Collectors.toList());
        }

        public void setDiscipline(ObservableList<String> checkedItems) {
            discipline = new ArrayList<>();
            for (String disc : checkedItems) {
                DisciplinaDto disciplinaDto = serviceHandler.getDisciplinaService().findByName(disc);
                if (disciplinaDto != null) {
                    int id = disciplinaDto.getId();
                    discipline.add(disciplinaDto);
                }
            }
        }
        public void setModified() {
            this.wasModified = true;
        }
        public boolean isModified() {
            return this.wasModified;
        }
    }


    @Data
    public static class Discipline {
        private int id;
        private String disciplina;
        private DisciplinaDto disciplinaDto;
        private boolean wasModified = false;
        private boolean wasDeleted = false;

        public Discipline() {
            this.id = 0;
        }

        public Discipline(DisciplinaDto disciplinaDto) {
            this.id = disciplinaDto.getId();
            this.disciplina = disciplinaDto.getDisciplina();
            this.disciplinaDto = disciplinaDto;
        }

        public void setModified() {
            this.wasModified = true;
        }

        public boolean isNew() {
            return this.id == 0;
        }

        public boolean isModified() {
            return this.wasModified;
        }

        public boolean isDeleted() {
            return this.wasDeleted;
        }

        public void setDeleted() {
            this.wasDeleted = true;
        }
    }
}
