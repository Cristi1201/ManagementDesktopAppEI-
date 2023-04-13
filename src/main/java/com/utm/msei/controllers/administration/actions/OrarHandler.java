package com.utm.msei.controllers.administration.actions;

import com.utm.msei.persistence.dto.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.util.Callback;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

import static com.utm.msei.Main.serviceHandler;

public class OrarHandler {

    private List<DisciplinaProfesorDto> allDiscProf = new ArrayList<>();

    private List<OrarDto> orarDtoList;
    private List<OrarTable> orarTable;
    private ClasaDto clasaDto;

    public static List<List<OrarTable>> sortByDurataAndZi(ObservableList<OrarTable> orar) {
        List<List<OrarTable>> orarWithDiscForDurata = new ArrayList<>();
        List<OrarTable> sortedOrar = new ArrayList<>();

        Comparator<DurataLectieDto> comparator = Comparator.comparingInt(o -> Integer.parseInt(o.getDurata().substring(0, o.getDurata().indexOf(":"))));
        List<DurataLectieDto> orderedDurata = serviceHandler.getDurataLectieService().getAll();
        Collections.sort(orderedDurata, comparator);

        for (DurataLectieDto durataDto : orderedDurata) {
            List<OrarTable> orarForDurata = orar.stream().filter(o -> o.getDurata().equals(durataDto.getDurata())).collect(Collectors.toList());
            orarForDurata.sort(new Comparator<OrarTable>() {
                @Override
                public int compare(OrarTable z1, OrarTable z2) {
                    List<String> orderedDays = Arrays.asList("Luni", "Marti", "Miercuri", "Joi", "Vineri");
                    return Integer.compare(orderedDays.indexOf(z1.getZi()), orderedDays.indexOf(z2.getZi()));
                }
            });

            sortedOrar.addAll(orarForDurata);
            orarWithDiscForDurata.add(new ArrayList<>(sortedOrar));
            sortedOrar.clear();
        }
        return orarWithDiscForDurata;
    }

    public List<OrarTable> getOrarForClass(String clasaName) {
        orarTable = new ArrayList<>();

        orarDtoList = serviceHandler.getOrarService().getForClass(clasaName);

        clasaDto = serviceHandler.getClasaService().getClassDto(clasaName);


        List<DurataLectieDto> durataLectieDtos = serviceHandler.getDurataLectieService().getAll();
        List<ZiSaptamanaDto> ziSaptamanaDtos = serviceHandler.getZiSaptamanaService().getAll();

        // TODO here must be all good, but if orar doesnt start from 1st durata, that will not be empty
//        orarDtoList = this.sortAscendingDaysAndDurata(orarDtoList);


        orarTable = orarDtoList.stream().map(o -> new OrarTable(o)).collect(Collectors.toList());

        orarTable = this.addBlankWhereNoDisc(durataLectieDtos, clasaName);

        return orarTable;


    }

    private List<OrarTable> addBlankWhereNoDisc(List<DurataLectieDto> durataLectieDtos, String clasaName) {
        List<ZiSaptamanaDto> ziSaptamanaDto = serviceHandler.getZiSaptamanaService().getAll();
        ziSaptamanaDto.sort(new Comparator<ZiSaptamanaDto>() {
            @Override
            public int compare(ZiSaptamanaDto z1, ZiSaptamanaDto z2) {
                List<String> orderedDays = Arrays.asList("Luni", "Marti", "Miercuri", "Joi", "Vineri");
                return Integer.compare(orderedDays.indexOf(z1.getZi()), orderedDays.indexOf(z2.getZi()));
            }
        });

        List<OrarTable> orarTableToAdd = new ArrayList<>();

        for (ZiSaptamanaDto ziSapt : ziSaptamanaDto) {
            for (DurataLectieDto durata : durataLectieDtos) {
                boolean dataExists = false;
                for (OrarTable orar : orarTable) {
                    if (orar.getZi().equals(ziSapt.getZi()) && orar.getDurata().equals(durata.getDurata())) {
                        dataExists = true;
                        break;
                    }
                }
                if (!dataExists) {
                    orarTableToAdd.add(new OrarTable(ziSapt, durata));
                }
            }
        }

        orarTable.addAll(orarTableToAdd);

        List<OrarTable> sortedOrar = new ArrayList<>();
        List<String> orderedDays = Arrays.asList("Luni", "Marti", "Miercuri", "Joi", "Vineri");
        for (String day : orderedDays) {
            List<OrarTable> orarForDay = orarTable.stream().filter(o -> o.getZiDto().getZi().equals(day)).collect(Collectors.toList());
            if (orarForDay.size() != 0) {
                Comparator<OrarTable> comparator = Comparator.comparingInt(o -> Integer.parseInt(o.getDurataDto().getDurata().substring(0, o.getDurataDto().getDurata().indexOf(":"))));
                Collections.sort(orarForDay, comparator);
                sortedOrar.addAll(orarForDay);
            }
        }
        orarTable = sortedOrar;

        int pos = durataLectieDtos.size() % 2 == 0 ? (durataLectieDtos.size() / 2) - 1 : durataLectieDtos.size() / 2;
        for (int i = 0; i < orarTable.size(); i++) {
            if (!(i % durataLectieDtos.size() == pos)) {
                orarTable.get(i).zi = "";
            }
        }

        return orarTable;
    }

    private List<OrarDto> sortAscendingDaysAndDurata(List<OrarDto> orarDto) {
        List<OrarDto> sortedOrar = new ArrayList<>();

        List<String> orderedDays = Arrays.asList("Luni", "Marti", "Miercuri", "Joi", "Vineri");
        for (String day : orderedDays) {
            List<OrarDto> orarForDay = orarDto.stream().filter(o -> o.getIdZi().getZi().equals(day)).collect(Collectors.toList());
            if (orarForDay.size() != 0) {
                Comparator<OrarDto> comparator = Comparator.comparingInt(o -> Integer.parseInt(o.getIdDurata().getDurata().substring(0, o.getIdDurata().getDurata().indexOf(":"))));
                Collections.sort(orarForDay, comparator);
                sortedOrar.addAll(orarForDay);
            }
        }

        return  sortedOrar;
    }

    private List<DurataLectieDto> sortDurataAsc(List<DurataLectieDto> durataLectieDto) {
        Comparator<DurataLectieDto> comparator = Comparator.comparingInt(o -> Integer.parseInt(o.getDurata().substring(0, o.getDurata().indexOf(":"))));
        Collections.sort(durataLectieDto, comparator);
        return durataLectieDto;
    }

    public List<String> getAllDiscProfAsString() {
        allDiscProf = serviceHandler.getDisciplinaProfesorService().getAll();
        List<String> allDiscProfList = new ArrayList<>();
        allDiscProf.forEach(dp -> {
            allDiscProfList.add(dp.getIdDisciplina().getDisciplina() + "," + dp.getIdProfesor().getIdUser().getNume() + " " + dp.getIdProfesor().getIdUser().getPrenume());
        });
        return allDiscProfList;
    }
    public List<DisciplinaProfesorDto> getAllDiscProf() {
        return allDiscProf;
    }

    public ProfesorDto getChoosedProfesorDto(String choosedValue) {
        List<ProfesorDto> profesorDtos = serviceHandler.getProfesorService().getAll();
        String prNume =choosedValue.split(",")[1].split(" ")[0];
        String prPrenume =choosedValue.split(",")[1].split(" ")[1];
        for (ProfesorDto p : profesorDtos) {
            if (p.getIdUser().getNume().equals(prNume) && p.getIdUser().getPrenume().equals(prPrenume)) {
                return p;
            }
        }
        return null;
    }

    public ClasaDto getClasa() {
        return clasaDto;
    }


    @Data
    public class OrarTable {

        private int id;
        private String zi;
        private ZiSaptamanaDto ziDto;
        private String durata;
        private DurataLectieDto durataDto;
        private String discProf;
        private DisciplinaProfesorDto discProfDto;


        private boolean wasModified = false;
        private boolean wasDeleted = false;

        public OrarTable(ZiSaptamanaDto ziSapt, DurataLectieDto durataLectieDto) {
            this.id = 0;
            this.ziDto = ziSapt;
            this.zi = ziSapt.getZi();
            this.durataDto = durataLectieDto;
            this.durata = durataLectieDto.getDurata();
        }

        public OrarTable(OrarDto orarDto) {
            this.id = orarDto.getId();
            this.zi = orarDto.getIdZi().getZi();
            this.ziDto = orarDto.getIdZi();
            this.durata = orarDto.getIdDurata().getDurata();
            this.durataDto = orarDto.getIdDurata();
            this.discProf = orarDto.getIdDiscProf().getIdDisciplina().getDisciplina() + "," + orarDto.getIdDiscProf().getIdProfesor().getIdUser().getNume() + " " + orarDto.getIdDiscProf().getIdProfesor().getIdUser().getPrenume();
            this.discProfDto = orarDto.getIdDiscProf();
        }
        public void setDiscProf(String discProf, List<DisciplinaProfesorDto> disciplinaProfesorDtos) {
            this.discProf = discProf;
            String disc = discProf.split(",")[0];
            String prNume = discProf.split(",")[1].split(" ")[0];
            String prPrenume = discProf.split(",")[1].split(" ")[1];
            this.discProfDto = null;
            for (DisciplinaProfesorDto d : disciplinaProfesorDtos) {
                if (d.getIdProfesor().getIdUser().getNume().equals(prNume) && d.getIdProfesor().getIdUser().getPrenume().equals(prPrenume) && d.getIdDisciplina().getDisciplina().equals(disc)) {
                    this.discProfDto = d;
                    break;
                }
            }
        }

        public void setModified() {
            this.wasDeleted = false;
            this.wasModified = true;
        }
        public boolean wasModified() {
            return this.wasModified;
        }

        public void setDeleted() {
            this.wasModified = false;
            this.wasDeleted = true;
        }
        public boolean wasDeleted() {
            return this.wasDeleted;
        }

        public boolean isNew() {
            return this.id == 0;
        }
    }

    //    example singleChoice comboBox for tableView
    public static class DiscProfComboBox implements Callback<TableColumn<OrarTable, String>, TableCell<OrarTable, String>> {
        private List<String> options;

        public DiscProfComboBox(List<String> options) {
            this.options = options;
        }
//        public FunctieCellFactory(ComboBox<String> options) {
//            this.options = options;
//        }

        @Override
        public TableCell<OrarTable, String> call(TableColumn<OrarTable, String> column) {
            return new ComboBoxTableCell<>(FXCollections.observableArrayList(options)) {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };
        }
    }
}
