package server.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayIncidentes {
    private Long incidente_id;
    private Boolean incidente_estado;
    private String nombreEntidad;
    private String nombreEstablecimiento;
    private String nombreServicio;

    public DisplayIncidentes(Long incidente_id, Boolean incidente_estado, String nombreEntidad, String nombreEstablecimiento, String nombreServicio) {
        this.incidente_id = incidente_id;
        this.incidente_estado = incidente_estado;
        this.nombreEntidad = nombreEntidad;
        this.nombreEstablecimiento = nombreEstablecimiento;
        this.nombreServicio = nombreServicio;
    }
}
