package models.Entities.MonitoreoServicios.Entidad;

import models.Entities.MonitoreoServicios.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "tipo_entidad")
public class TipoEntidad extends Persistente {
    @Column(name = "leyenda")
    private String leyenda;
    public TipoEntidad(String leyenda){
        this.leyenda = leyenda;
    }
    public TipoEntidad(){}
}
