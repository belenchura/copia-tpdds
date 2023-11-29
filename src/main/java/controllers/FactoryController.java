package controllers;

import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import models.Entities.MonitoreoServicios.Comunidad.ServicioMiembro;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Entidad.Entidad;
import models.Entities.MonitoreoServicios.Establecimiento.Establecimiento;
import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Entities.MonitoreoServicios.Servicio.Servicio;
import models.Repositories.RepositorioEstablecimientos;
import models.Repositories.RepositorioIncidentes;
import models.Repositories.RepositorioMiembros;
import models.Repositories.RepositorioServicios;
import models.Repositories.RepositorioTemplate;

public class FactoryController {
    public static Object controller(String nombre) {
        Object controller = null;
        switch (nombre) {
            case "Comunidades": controller = new ComunidadesController(new RepositorioTemplate<Comunidad>(Comunidad.class)); break;

            case "Miembro": controller = new MiembrosComunidadController(new RepositorioMiembros(Miembro.class)); break;
            case "Incidentes": controller = new IncidentesController(new RepositorioTemplate<Incidente>(Incidente.class)); break;
            case "Entidades": controller = new EntidadesController(new RepositorioTemplate<Entidad>(Entidad.class)); break;
            case "Establecimientos": controller = new EstablecimientosController(new RepositorioEstablecimientos(Establecimiento.class)); break;
            case "Servicios": controller = new ServiciosController(new RepositorioServicios(Servicio.class)); break;
            case "Descripcion": controller = new DescripcionController(); break;
            case "Personas": controller = new PersonasController(new RepositorioTemplate<Persona>(Persona.class)); break;
            case "ServicioMiembro": controller = new ServicioMiembroController(new RepositorioTemplate<ServicioMiembro>(ServicioMiembro.class)); break;
            case "Ranking": controller = new RankingsController(new RepositorioIncidentes(Incidente.class),new RepositorioTemplate<Entidad>(Entidad.class)); break;


        }
        return controller;
    }
}
