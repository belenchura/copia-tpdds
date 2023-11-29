package models.Entities.Notificador;

import models.Entities.MonitoreoServicios.Incidente.Incidente;
import models.Entities.MonitoreoServicios.Persona.Persona;
import models.Repositories.RepositorioTemplate;

import java.util.List;
import java.util.stream.Collectors;

public class Distancia {
    private static RepositorioTemplate<Incidente> repoIncidentes;
    public Distancia(RepositorioTemplate<Incidente> repoIncidentes){
        this.repoIncidentes=repoIncidentes;
    }
    public static Incidente obtenerIncidenteMasCercano(Persona persona){
        List<Incidente> incidentesPosibles= repoIncidentes.buscarTodos().stream().
                filter(incidente -> incidente.getComunidad().getMiembros().stream().anyMatch(m->m.getPersona()==persona) ||
                        persona.getInteres().getServicios().contains(incidente.getPrestacion().getServicio()))
                .collect(Collectors.toList());
        return incidentesPosibles.get(0);
    }
    private int calcularDistanciaPersonaIncidente(Persona persona,Incidente incidente){
        int distancia=0;
        //TODO: completar la condicion en un futuro
        persona.getUbicacionActual();
        return 30;
    }
}