package models.Entities.MonitoreoServicios;

import models.Entities.MonitoreoServicios.Establecimiento.Establecimiento;
import models.Entities.MonitoreoServicios.Servicio.Servicio;

import javax.persistence.*;

@Entity
@Table(name = "prestacion")
public class Prestacion extends Persistente{
    @Column(name = "estado")
    private Boolean estado;
    @ManyToOne
    @JoinColumn(name = "establecimiento_id", referencedColumnName = "id")
    private Establecimiento establecimiento;

    @ManyToOne
    @JoinColumn(name = "servicio_id", referencedColumnName = "id")
    private Servicio servicio;

    public Prestacion(){
    }
    public Prestacion(Servicio serv, Establecimiento establecimiento){
        this.servicio=serv;
        this.establecimiento = establecimiento;
    }
    public Boolean getEstado() {
        return estado;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public Servicio getServicio() {
        return servicio;
    }
}
