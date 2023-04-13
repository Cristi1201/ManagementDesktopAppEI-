package com.utm.msei.controllers.administration.actions;

import com.utm.msei.persistence.dto.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.utm.msei.Main.serviceHandler;

public class EleviHandler {

    private ClasaDto clasa;
    private List<ElevTable> eleviOfClass = new ArrayList<>();

    public EleviHandler(String clasaName) {
        this.clasa = serviceHandler.getClasaService().getClasa(clasaName);
    }

    public List<ElevTable> getAllElevi() {

        List<ElevDto> eleviDto = serviceHandler.getElevService().getElevi(this.clasa);

        eleviDto.forEach(eDto -> {
            eleviOfClass.add(new ElevTable(eDto));
        });

        return eleviOfClass;
    }

    public void setDeleted(ElevTable selected) {
        for (ElevTable e : eleviOfClass) {
            if (e.getId() == selected.getId()) {
                e.setDeleted();
                break;
            }
        }
    }

    public ClasaDto getClasa() {
        return this.clasa;
    }


    @Data
    public static class ElevTable {
        private ElevDto elevDto;
        private UserDto userDto;
        private int id;
        private String nume;
        private String prenume;
        private String email;
        private String telefon;
        private String idnp;
        private LocalDate dataNastere;
        private TataDto tataDto;
        private MamaDto mamaDto;
        private boolean wasModified = false;
        private boolean wasDeleted = false;

        public ElevTable() {
            this.id = 0;
        }

        public ElevTable(ElevDto elevDto) {
            this.elevDto = elevDto;
            this.id = elevDto.getId();
            this.nume = elevDto.getIdUser().getNume();
            this.prenume = elevDto.getIdUser().getPrenume();
            this.email = elevDto.getIdUser().getEmail();
            this.telefon = elevDto.getIdUser().getTelefon();
            this.idnp = elevDto.getIdUser().getIdnp();
            this.dataNastere = elevDto.getIdUser().getDataNastere();
            this.tataDto = elevDto.getIdTata();
            this.mamaDto= elevDto.getIdMama();
            this.userDto = elevDto.getIdUser();
        }

        public void setModified() {
            this.wasModified = true;
            this.wasDeleted = false;
        }
        public boolean isModified() {
            return this.wasModified;
        }

        public void setDeleted() {
            this.wasDeleted = true;
            this.wasModified = false;
        }
        public boolean isDeleted() {
            return this.wasDeleted;
        }
        public boolean isNew() {
            return this.id == 0;
        }
    }




    public static class DiriginteClasaComboBoxHandler {

        private ClasaDto clasa;
        private List<ProfesorDto> profesorDtos;
        private ProfesorDto diriginteOfClass;
        private String dirNume;
        private String dirPrenume;


        public DiriginteClasaComboBoxHandler(String clasaName) {
            this.clasa = serviceHandler.getClasaService().getClasa(clasaName);
            this.diriginteOfClass = this.clasa.getProfesorDto();
            this.profesorDtos = serviceHandler.getProfesorService().getAll();
            if (this.diriginteOfClass != null) {
                this.dirNume = this.diriginteOfClass.getIdUser().getNume();
                this.dirPrenume = this.diriginteOfClass.getIdUser().getPrenume();
            } else {
                this.dirNume = null;
                this.dirPrenume = null;
            }

        }

        public ProfesorDto getDirForClass() {
            return this.diriginteOfClass;
        }


        public  List<ProfesorDto> getAllProfs() {
            return serviceHandler.getProfesorService().getAll();
        }

        public void updateDirForClass(String profName) {
            String nume = profName.split(" ")[0];
            String prenume = profName.split(" ")[1];
            if (!nume.equals(this.dirNume) && !prenume.equals(this.dirPrenume)) {
                ProfesorDto newDiriginte = null;
                for (ProfesorDto p : this.profesorDtos) {
                    if (p.getIdUser().getNume().equals(nume) && p.getIdUser().getPrenume().equals(prenume)) {
                        newDiriginte = p;
                        break;
                    }
                }
                serviceHandler.getClasaService().updateDiriginteForClass(newDiriginte, this.clasa);
            }
        }
    }
}
