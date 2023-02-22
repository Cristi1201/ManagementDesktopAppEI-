package com.utm.msei;

import com.utm.msei.executor.ExecuteI;
import com.utm.msei.executor.JavaFxInitializer;
import com.utm.msei.handler.ServiceHandler;
import com.utm.msei.persistence.service.ZiSaptamanaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.utm.msei.persistence.service.*;

@SpringBootApplication
public class Main implements ExecuteI {

    @Autowired
    private AbsenteService absenteService;
    @Autowired
    private ActivitateService activitateService;
    @Autowired
    private AdministratieService administratieService;
    @Autowired
    private ClasaService clasaService;
    @Autowired
    private DisciplinaProfesorService disciplinaProfesorService;
    @Autowired
    private DisciplinaService disciplinaService;
    @Autowired
    private DurataLectieService durataLectieService;
    @Autowired
    private ElevService elevService;
    @Autowired
    private MesajeService mesajeService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private OrarService orarService;
    @Autowired
    private ParintiService parintiService;
    @Autowired
    private ProfesorService profesorService;
    @Autowired
    private  TemaAcasaService temaAcasaService;
    @Autowired
    private UserService userService;
    @Autowired
    private ZiSaptamanaService ziSaptamanaService;

    public static ServiceHandler serviceHandler = new ServiceHandler();

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String[] args) {
        saveServices();
        JavaFxInitializer.launch();
    }

    private void saveServices() {
        serviceHandler.setAbsenteService(absenteService);
        serviceHandler.setActivitateService(activitateService);
        serviceHandler.setAdministratieService(administratieService);
        serviceHandler.setClasaService(clasaService);
        serviceHandler.setDisciplinaProfesorService(disciplinaProfesorService);
        serviceHandler.setDisciplinaService(disciplinaService);
        serviceHandler.setDurataLectieService(durataLectieService);
        serviceHandler.setElevService(elevService);
        serviceHandler.setMesajeService(mesajeService);
        serviceHandler.setNoteService(noteService);
        serviceHandler.setOrarService(orarService);
        serviceHandler.setParintiService(parintiService);
        serviceHandler.setProfesorService(profesorService);
        serviceHandler.setTemaAcasaService(temaAcasaService);
        serviceHandler.setUserService(userService);
        serviceHandler.setZiSaptamanaService(ziSaptamanaService);
    }
}
