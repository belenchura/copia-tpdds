package server.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayServicios {
    private Long prestacion_id;
    private String nombreServicio;
    public DisplayServicios(Long prestacion_id, String nombreServicio){
        this.prestacion_id = prestacion_id;
        this.nombreServicio = nombreServicio;
    }
}
