package models.Entities.CalculoImpactoDeEntidad.entities.solicitud;

import java.util.List;

public class EntidadSolicitud {
    public String name;
    public int ammountOfAffectedMembers;
    public List<IncidenteSolicitud> incidents;
    public void setIncidents(List<IncidenteSolicitud> incidentesRecibidos){
        this.incidents.addAll(incidentesRecibidos);
    }
}
