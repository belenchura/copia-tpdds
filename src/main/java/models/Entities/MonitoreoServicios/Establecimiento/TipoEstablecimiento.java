package models.Entities.MonitoreoServicios.Establecimiento;

import models.Entities.MonitoreoServicios.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@Table(name = "tipo_establecimiento")
public class TipoEstablecimiento extends Persistente {
    @Column(name = "leyenda")
    private String leyenda;
    public TipoEstablecimiento() {}

    public TipoEstablecimiento(String leyenda) {
        this.leyenda = leyenda;
    }
}
