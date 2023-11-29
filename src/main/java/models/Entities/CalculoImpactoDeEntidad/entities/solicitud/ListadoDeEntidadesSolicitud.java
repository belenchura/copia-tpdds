package models.Entities.CalculoImpactoDeEntidad.entities.solicitud;

import java.util.List;

public class ListadoDeEntidadesSolicitud {
    public List<EntidadSolicitud> entities;
    public void setEntities(List<EntidadSolicitud> entidades){
        this.entities.addAll(entidades);
    }
}
