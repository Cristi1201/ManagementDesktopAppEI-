package com.utm.msei.controllers.administration.actions;

import com.utm.msei.persistence.dto.ElevDto;
import com.utm.msei.persistence.dto.MamaDto;
import com.utm.msei.persistence.dto.TataDto;
import com.utm.msei.persistence.mapper.MamaMapper;
import lombok.Data;
import lombok.Getter;

import java.util.List;

import static com.utm.msei.Main.serviceHandler;

public class ParintiHandler {

    @Getter
    List<TataDto> allTata;
    @Getter
    List<MamaDto> allMama;
    private ElevDto elevDto;

    public ParintiHandler(EleviHandler.ElevTable elev) {
        this.allTata = serviceHandler.getTataService().getAll();
        this.allMama = serviceHandler.getMamaService().getAll();
        this.elevDto = elev.getElevDto();
    }

    public TataDto getTataDto(EleviHandler.ElevTable elev) {
        return elev.getTataDto();
    }
    public TataDto getTataDto(String tata) {
       String nume = tata.split(" ")[0];
       String prenume = tata.split(" ")[1].split(",")[0];
       String telefon = tata.split(" ")[1].split(",")[1];
       for (TataDto t : allTata) {
           if (t.getNume().equals(nume) && t.getPrenume().equals(prenume) && t.getTelefon().equals(telefon)) {
               return t;
           }
        }
        return null;
    }


    public MamaDto getMamaDto(EleviHandler.ElevTable person) {
        return person.getMamaDto();
    }
    public MamaDto getMamaDto(String mama) {
        String nume = mama.split(" ")[0];
        String prenume = mama.split(" ")[1].split(",")[0];
        String telefon = mama.split(" ")[1].split(",")[1];
        for (MamaDto m : allMama) {
            if (m.getNume().equals(nume) && m.getPrenume().equals(prenume) && m.getTelefon().equals(telefon)) {
                return m;
            }
        }
        return null;
    }

    public boolean saveParent(String numeTata, String prenumeTata, String telefonTata, String numeMama, String prenumeMama, String telefonMama) {
        TataDto tata = getTataDto(numeTata, prenumeTata, telefonTata);
        MamaDto mama = getMamaDto(numeMama, prenumeMama, telefonMama);

        if (tata == null) {
            tata = createTata(numeTata, prenumeTata, telefonTata);
        }
        tata = serviceHandler.getTataService().save(tata);
        if (mama == null) {
            mama = createMama(numeMama, prenumeMama, telefonMama);
        }
        mama = serviceHandler.getMamaService().save(mama);
        int i = serviceHandler.getElevService().updateTata(elevDto.getId(), tata);
        int j = serviceHandler.getElevService().updateMama(elevDto.getId(), mama);
        if (i == 1 && j == 1) {
            return true;
        }
        return false;
    }
    private TataDto getTataDto(String numeTata, String prenumeTata, String telefonTata) {
        for (TataDto t : allTata) {
            if (t.getNume().equals(numeTata) && t.getPrenume().equals(prenumeTata) && t.getTelefon().equals(telefonTata)) {
                return t;
            }
        }
        return null;
    }
    private TataDto createTata(String numeTata, String prenumeTata, String telefonTata) {
        TataDto tataDto = new TataDto();
        tataDto.setNume(numeTata);
        tataDto.setPrenume(prenumeTata);
        tataDto.setTelefon(telefonTata);
        return tataDto;
    }
    private MamaDto getMamaDto(String numeMama, String prenumeMama, String telefonMama) {
        for (MamaDto m : allMama) {
            if (m.getNume().equals(numeMama) && m.getPrenume().equals(prenumeMama) && m.getTelefon().equals(telefonMama)) {
                return m;
            }
        }
        return null;
    }
    private MamaDto createMama(String numeMama, String prenumeMama, String telefonMama) {
        MamaDto mamaDto = new MamaDto();
        mamaDto.setNume(numeMama);
        mamaDto.setPrenume(prenumeMama);
        mamaDto.setTelefon(telefonMama);
        return mamaDto;
    }
}
