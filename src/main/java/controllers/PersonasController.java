package controllers;

import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Repositories.RepositorioTemplate;

public class PersonasController extends Controller{
    private RepositorioTemplate<Persona> repoPersonas;

    public PersonasController(RepositorioTemplate<Persona> repoPersonas){
        this.repoPersonas=repoPersonas;
    }
}
