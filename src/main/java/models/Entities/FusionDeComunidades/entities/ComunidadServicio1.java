package models.Entities.FusionDeComunidades.entities;

import models.Entities.FusionDeComunidades.entities.solicitud.EstablecimientoSolicitud;
import models.Entities.FusionDeComunidades.entities.solicitud.MiembroSolicitud;
import models.Entities.FusionDeComunidades.entities.solicitud.ServicioSolicitud;

import java.util.List;

public class ComunidadServicio1 {
    public String nombre;
    public List<MiembroSolicitud> miembros;
    public int gradoDeConfianza;
    public List<EstablecimientoSolicitud> establecimientos;
    public List<ServicioSolicitud> servicios;
    public void setMiembro(MiembroSolicitud miembro){
        this.miembros.add(miembro);
    }
    public void setEstablecimiento(EstablecimientoSolicitud establecimiento){
        this.establecimientos.add(establecimiento);
    }
    public void setServicio(ServicioSolicitud servicio){
        this.servicios.add(servicio);
    }
}
