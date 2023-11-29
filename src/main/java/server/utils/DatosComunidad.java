package server.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import models.Entities.MonitoreoServicios.Comunidad.Comunidad;
import models.Entities.MonitoreoServicios.Comunidad.Miembro;
import server.routes.ComunidadRouter;


@Getter
@Setter
public class DatosComunidad {
    private Long id;
    private String nombreComunidad;
    private Boolean esMiembro;
    private Integer cantidadDeMiembros;
    public DatosComunidad(Comunidad comunidad,Boolean esMiembro){
        this.id= comunidad.getId();
        this.nombreComunidad= comunidad.getNombre();
        this.cantidadDeMiembros=comunidad.getMiembros().size();
        this.esMiembro=esMiembro;
    }
}
