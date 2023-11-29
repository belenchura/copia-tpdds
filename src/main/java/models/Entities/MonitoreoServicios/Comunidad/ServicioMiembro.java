package models.Entities.MonitoreoServicios.Comunidad;

import com.mysql.cj.MysqlConnection;
import models.Entities.MonitoreoServicios.Persistente;
import models.Entities.MonitoreoServicios.Servicio.Servicio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "servicio_miembro")
public class ServicioMiembro extends Persistente {
    @ManyToOne
    @JoinColumn(name = "miembro_id", referencedColumnName = "id")
    private Miembro miembro;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_miembro")
    private TipoMiembro tipoMiembro;

    public ServicioMiembro(){}
    public Servicio getServicio() {
        return servicio;
    }

    public Miembro getMiembro() {
        return miembro;
    }

    public String getTipoMiembroConvertido() {
        return (this.tipoMiembro == TipoMiembro.OBSERVADOR) ? "Observador" : "Afectado";
    }

    public void setTipoMiembro(TipoMiembro tipoMiembro) {
        this.tipoMiembro = tipoMiembro;
    }

}
