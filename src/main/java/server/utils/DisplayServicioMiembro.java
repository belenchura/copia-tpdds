package server.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayServicioMiembro {
    private Long servicio_miembro_id;
    private String nombreServicio;
    private String tipoMiembro;

    public DisplayServicioMiembro(Long servicio_miembro_id, String nombreServicio, String tipoMiembro) {
        this.servicio_miembro_id = servicio_miembro_id;
        this.nombreServicio = nombreServicio;
        this.tipoMiembro = tipoMiembro;
    }
}
