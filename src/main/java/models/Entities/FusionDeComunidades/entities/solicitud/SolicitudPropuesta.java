package models.Entities.FusionDeComunidades.entities.solicitud;

import models.Entities.FusionDeComunidades.entities.ComunidadServicio1;

import java.util.List;

public class SolicitudPropuesta {
    public ComunidadServicio1 comunidad;
    public List<ComunidadServicio1> lista_comunidades;
    public void setComunidad(ComunidadServicio1 comunidad){
        this.lista_comunidades.add(comunidad);
    }
}
